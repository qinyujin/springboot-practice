package com.redis01.controller;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author :覃玉锦
 * @create :2021-02-10 12:45:00
 * 存在的问题：
 * 1、没有加锁->可以考虑加synchronized或者lock的tryLock。 问题：单机锁不能在分布式环境下使用。解决：可以使用redis的setnx来
 * 实现锁，setnx有则不设置并且返回false，可以用它来判断。
 * 2、解锁的delete应该要写在finally里，比如有异常导致一直没有释放锁。
 * 3、如果宕机了不进入finally，那么同样会释放不了锁。解决：设置过期时间。
 * 4、如果设置过期时间不是和设置锁在一行的话，没有原子性，有可能宕机导致没有设置上时间。解决：使用带时间参数的setnx方法。
 * 5、如果业务时间过长，没有通过finally释放锁，而是key过期了，那么业务还没结束就把锁释放了，假设现在线程为A，其他线程为B，那么
 * 有可能A线程redis锁过期，B上了锁，然后A执行到finally把B上的锁给删了。解决：通过对比value来删除，如果value和自己存的value
 * 一致，说明是自己的锁。
 * 6、finally中的get和delete不是原子性的。解决：方式一是lua脚本，可以去官网搜。方式二是使用watch+事务，相当于乐观锁。
 * 7、关于锁过期时间的问题，实际业务完成时间难以估计，因此过期时间的值很难设置。解决：续期的思想。实现可以用redisson。
 * 8、finally使用redisson的时候，需要加两个判断，islocked和isheldByCurrentThread
 */
@RestController
public class GoodsController {

    @Autowired
    private StringRedisTemplate template;

    //通常使用连接池来获取jedis连接，这里简单演示就不用了
    private Jedis jedis = new Jedis("192.168.48.144", 6379);

    @Autowired
    private Redisson redisson;

    private String REDIS_LOCK = "atguigulock";

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/buy_goods")
    public String buy_goods() {

        return redisLockImpl();
//        return redissonImpl();
    }

    /**
     * redis实现分布式锁
     *
     * @return
     */
    public String redisLockImpl() {
        String value = UUID.randomUUID().toString() + Thread.currentThread().getName();
        //这里通过setnx来获取bool值，如果值已存在，那么说明已被锁住。
//        Boolean flag = template.opsForValue().setIfAbsent(REDIS_LOCK, value);
        //为了避免宕机不进入finally而无法解锁，设置一个过期时间,但是这样保证不了原子性，因为先设置了key才设置时间，如果此时宕机
        //没有设置上时间，结果一样。如何解决：使用带时间的setnx。
//        template.expire(REDIS_LOCK, 10L, TimeUnit.SECONDS);
        Boolean flag = template.opsForValue().setIfAbsent(REDIS_LOCK, value, 10L, TimeUnit.SECONDS);
        if (!flag) {
            return "抢锁失败!!";
        }

        try {
            String result = template.opsForValue().get("goods:001");
            int goodsNumber = result == null ? 0 : Integer.parseInt(result);

            int realNumber = goodsNumber - 1;
            template.opsForValue().set("goods:001", String.valueOf(realNumber));
            if (goodsNumber > 0) {
                System.out.println("购买到货物，剩余货物：" + realNumber + " 请求的端口号：" + serverPort);
                //如果成功购买那么释放锁
                return "购买到货物，剩余货物：" + realNumber + " 请求的端口号：" + serverPort;
            } else {
                System.out.println("货物已卖完。");
                return "货物已卖完。";
            }
        } finally {
            //这里get和delete非原子性，有可能存在并发问题，可以采用lua脚本解决
//            if (template.opsForValue().get(REDIS_LOCK).equals(value)) {
//                template.delete(REDIS_LOCK);
//            }

            //使用while+watch+事务来解决原子性。
//            while (true) {
//                //先监控锁，防止被改动
//                template.watch(REDIS_LOCK);
//                //可以开启事务
//                template.setEnableTransactionSupport(true);
//                if (template.opsForValue().get(REDIS_LOCK).equalsIgnoreCase(value)) {
//                    //开启事务
//                    template.multi();
//                    template.delete(REDIS_LOCK);
//                    List<Object> list = template.exec();
//                    //如果删除失败可以重试
//                    if (list == null) {
//                        continue;
//                    }
//                }
//                //如果成功删除了
//                template.unwatch();
//                break;
//            }

            //判断value并且删除lockKey,lua脚本原子性执行
            String unlockLuaScript = "if redis.call('get',KEYS[1]) == false then return 1 "
                    + "elseif redis.call('get',KEYS[1]) == ARGV[1] then "
                    + "return redis.call('del',KEYS[1]) else return 2 end";

            Object eval = jedis.eval(unlockLuaScript, Arrays.asList(REDIS_LOCK), Arrays.asList(value));
            System.out.println("eval : " + eval);
        }
    }

    /**
     * 使用redisson提供的分布式锁,该包还提供分布式读写锁
     *
     * @return
     */
    public String redissonImpl() {

        String value = UUID.randomUUID().toString() + Thread.currentThread().getName();

        //使用redisson来实现分布式锁
        RLock redissonLock = redisson.getLock(REDIS_LOCK);
        redissonLock.lock();

        try {
            String result = template.opsForValue().get("goods:001");
            int goodsNumber = result == null ? 0 : Integer.parseInt(result);

            int realNumber = goodsNumber - 1;
            template.opsForValue().set("goods:001", String.valueOf(realNumber));
            if (goodsNumber > 0) {
                System.out.println("购买到货物，剩余货物：" + realNumber + " 请求的端口号：" + serverPort);
                //如果成功购买那么释放锁
                return "购买到货物，剩余货物：" + realNumber + " 请求的端口号：" + serverPort;
            } else {
                System.out.println("货物已卖完。");
                return "货物已卖完。";
            }
        } finally {
            //使用redisson实现分布式锁
            if (redissonLock.isLocked() && redissonLock.isHeldByCurrentThread()) {
                redissonLock.unlock();
            }
        }
    }
}

