package com.aimer.component;

import com.aimer.component.dao.CourseDao;
import com.aimer.component.dao.UserCourseDao;
import com.aimer.component.dao.UserDao;
import com.aimer.component.service.UserService;
import com.aimer.component.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    @Transactional
    void testAll() throws Exception {
        userService.update();
    }
}