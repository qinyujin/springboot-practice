package com.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author :覃玉锦
 * @create :2020-10-13 10:38:00
 */
@Component
@Aspect
@Slf4j
public class MyAspect {

    @Pointcut("execution(* com.aop.service..*.*(..))")
    public void pointcut(){}

    @Before(value = "pointcut()")
    public void before(){
        log.info("before");
    }

    @After("pointcut()")
    public void after(){
        log.info("after");
    }

    /*@AfterReturning
    public void afterReturn(){
        log.info("AfterReturning");
    }*/

    /*@AfterThrowing
    public void afterThrow(){
        log.info("AfterThrowing");
    }*/
}
