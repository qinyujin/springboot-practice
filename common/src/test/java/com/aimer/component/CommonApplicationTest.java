package com.aimer.component;

import com.aimer.component.dao.CourseDao;
import com.aimer.component.dao.UserCourseDao;
import com.aimer.component.dao.UserDao;
import com.aimer.component.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author :覃玉锦
 * @create :2020-09-23 13:48:00
 */
@SpringBootTest
class CommonApplicationTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserCourseDao userCourseDao;

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    void testAll(){
        redisUtil.del("key");
        System.out.println(redisUtil.keys("*"));
    }
}