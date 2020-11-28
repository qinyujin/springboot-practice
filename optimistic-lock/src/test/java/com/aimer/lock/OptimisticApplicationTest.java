package com.aimer.lock;

import com.aimer.lock.entity.User;
import com.aimer.lock.mapper.UserMapper;
import com.aimer.lock.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author :覃玉锦
 * @create :2020-09-21 11:29:00
 */
@SpringBootTest
class OptimisticApplicationTest {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    private static int retryCount = 0;

    @Test
    void test1(){
        int retryCount = 0;
        User user = new User();
        user.setId(1);
        user.setName("发条asfs");
        user.setPwd("4564213");
        System.out.println(userService.updateUserById(user));
    }
}