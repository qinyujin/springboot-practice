package com.aimer.component.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author :覃玉锦
 * @create :2021-01-20 21:58:00
 */
@Service
public class TestService {
    @Resource
    TestService testService;

    public void method() throws Exception {
        update1();

        testService.update2();

         testService.update3();

         testService.update4();

         testService.update5();
    }
    @Transactional
    public void update1() throws Exception {
        //SQL_1
        throw new Exception();
    }
    @Transactional
    public void update2() throws Exception {
        //SQL_2
        throw new Exception();
    }
    @Transactional
    private void update3() throws Exception {
        //SQL_3
        throw new Exception();
    }
    @Transactional
    public void update4() {
        //SQL_4
        throw new Error();
    }
    @Transactional
    public void update5() throws IOException {
        //SQL_5
        throw new IOException();
    }
}
