package com.deploy.controller;

import com.aimer.component.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

/**
 * @author :覃玉锦
 * @create :2020-10-10 13:46:00
 */
@RestController
@Slf4j
public class TestController {
    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("test")
    public Map test(){
        Set keys = redisUtil.keys("*");
        log.info("redis的keys：{}",keys);
        return Map.of("keys",keys);
    }
}
