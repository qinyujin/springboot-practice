package com.boss.vertifier.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

/**
 * @author :覃玉锦
 * @create :2020-09-11 13:37:00
 */
@RestController
@Slf4j
public class LoginController {

    @GetMapping("test")
    public String login(@RequestParam("vertifyCode")String code){

        Jedis jedis = new Jedis();
        jedis.auth("123456");
        String verifyCode = jedis.get("VerifyCode");
        if(verifyCode==null){
            return "验证码过期，请刷新！";
        }
        log.info("VerifyCode:"+verifyCode);
        log.info("user Code:"+code);

        if(code.equalsIgnoreCase(verifyCode))return "验证码正确！";
        else return "验证码错误!";
    }
}
