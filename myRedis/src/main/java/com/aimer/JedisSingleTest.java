package com.aimer;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author :覃玉锦
 * @create :2023-12-04 20:23:00
 * 单机redis
 */
public class JedisSingleTest {
    public static void main(String[] args) {

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMinIdle(5);

        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "192.168.48.144", 6380, 3000, null);

        Jedis jedis = null;

        try {
            //连接池里拿一个连接
            jedis = jedisPool.getResource();

            System.out.println(jedis.set("single", "yujin"));
            System.out.println(jedis.get("single"));

            //管道可以一次性发送批量的操作，减少java客户端和redis的通信次数
            /*Pipeline pl = jedis.pipelined();
            for (int i = 0; i < 10; i++) {
                pl.incr("pipelinekey");
                pl.set("yujin" + i, "yujin");
            }

            List<Object> results = pl.syncAndReturnAll();
            System.out.println(results);*/

            //lua脚本模拟商品减库存的原子操作
            //lua脚本命令执行方式：redis‐cli ‐‐eval /tmp/test.lua , 10
            /*String script = "  local count = redis.call('get', KEYS[1]) " +
                    " local a = tonumber(count) " +
                    " local b = tonumber(ARGV[1]) " +
                    " if a >= b then " +
                    " redis.call('set', KEYS[1], a‐b) " +
                    " return 1 " +
                    " end " +
                    " return 0 ";

            Object obj = jedis.eval(script, Arrays.asList("product_count_10016"), Arrays.asList("10"));
            System.out.println(obj);*/

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                //归还连接到池子里,并不是真正的关闭
                jedis.close();
            }
        }

    }
}
