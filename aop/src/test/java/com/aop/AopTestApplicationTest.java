package com.aop;

import com.aop.service.AopService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author :覃玉锦
 * @create :2020-10-13 11:07:00
 */
@SpringBootTest
public class AopTestApplicationTest {

    @Autowired
    private AopService aopService;

    @Test
    public void test1(){
        aopService.aopTest(0);
    }
}