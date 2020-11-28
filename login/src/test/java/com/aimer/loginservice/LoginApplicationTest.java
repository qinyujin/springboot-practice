package com.aimer.loginservice;

import com.aimer.component.util.RedisUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Base64;

/**
 * @author :覃玉锦
 * @create :2020-08-11 16:55:00
 */
@SpringBootTest
class LoginApplicationTest {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void test1() throws JsonProcessingException {
        String random = RandomStringUtils.random(16);
        String s = Base64.getEncoder().encodeToString(random.getBytes());
        System.out.println(s);
    }
}