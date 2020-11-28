package com.aimer.controller;

import com.aimer.entity.User;
import com.aimer.util.RedisUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author :覃玉锦
 * @create :2020-08-11 16:34:00
 */
@RestController
public class GetUserController {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("getUser")
    public Map getUser() throws JsonProcessingException {
        //在客户端中拿到redis的key，并且通过key来redis中查找是否登录。
        Object user = redisUtil.get("user");
        User user1 = objectMapper.readValue(String.valueOf(user), User.class);
        return Map.of("user",user1);
    }

    @GetMapping("info")
    public Map getInfo(){
        return Map.of("test","看看拦截器为什么不能抛出异常");
    }
}
