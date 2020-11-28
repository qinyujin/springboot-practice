package com.boss.vertifier;

import com.boss.vertifier.component.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Iterator;
import java.util.Set;

/**
 * @author :覃玉锦
 * @create :2020-09-26 14:51:00
 */
@SpringBootTest
class VertifierApplicationTest {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 模拟多用户登录，多用户发送请求验证码，把验证码放在set集合中，存入的value为验证码+时间戳
     * ，如果时间戳不超过5分钟则可以
     * 思路：1、默认set集合为10分钟过期，验证码为5分钟过期
     * 2、存入set集合，存入的value为code+timeStamp
     * 3、校验验证码时从set中遍历，取到code和timeStamp，都一致才验证成功
     */
    @Test
    void test1() {
        String key = "verifyCodeList";
        //时间戳，请求发送时携带
        String timeStamp = "1601105526949";
        //模拟4个验证码
        String[] verifyCodeList = new String[4];
        verifyCodeList[0] = "G36C";
        verifyCodeList[1] = "AK47";
        verifyCodeList[2] = "M4A1";
        verifyCodeList[3] = "M762";

//        redisUtil.sSetAndTime(key, 600, verifyCodeList[1]+timeStamp);

        Set<Object> objects = redisUtil.sGet(key);
        Iterator<Object> iterator = objects.iterator();
        while (iterator.hasNext()) {
            String vCode = (String) iterator.next();
            String code = vCode.substring(0, vCode.length()-13);
            String time = vCode.substring(4);
            Long t = Long.valueOf(time);
            System.out.println("存储的code："+code);
            System.out.println("code对应时间："+time);
            //验证码正确且时间不超5分钟
            System.out.println("验证码过去了："+(System.currentTimeMillis() - t) / 1000+"秒");
            if(verifyCodeList[0].equalsIgnoreCase(code) && (System.currentTimeMillis()-t)/1000<300){
                System.out.println("验证通过！");
                return;
            }
        }
        System.out.println("验证码错误或者超时");
    }

    //1601104483266
    @Test
    void test2() {
        String key = "VerifyCodeList";
        /*redisUtil.sSetAndTime(key, 120, "HV4F1234567891012");
        System.out.println(redisUtil.get(key));
        System.out.println(redisUtil.getExpire(key));
        System.out.println(redisUtil.keys("*"));*/

//        redisTemplate.opsForSet().add("VerifyCodeList", "RIOS1234567891112");
//        System.out.println(redisUtil.sGet(key));
        System.out.println(redisUtil.sSetAndTime(key, 120, "FSIJ9578645315964"));
    }
}