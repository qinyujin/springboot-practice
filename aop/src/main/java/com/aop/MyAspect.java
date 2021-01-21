package com.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
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

    //public com.aop.service 所有子包（..） 所有类（*） 所有方法名（.*） 所有参数(..)
    @Pointcut("execution(* com.aop.service..*.*(..))")
    public void pointcut(){}

    //在通知中引用切入点表达式，对方法进行切入
    @Before(value = "pointcut()")
    public void before(){
        log.info("before");
    }

    @After("pointcut()")
    public void after(){
        log.info("after");
    }

    @AfterReturning("pointcut()")
    public void afterReturn(){
        log.info("AfterReturning");
    }

    @AfterThrowing("pointcut()")
    public void afterThrow(){
        log.info("AfterThrowing");
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Around before");
        Object[] args = joinPoint.getArgs();
        //切入方法，可以在这里修改参数
        Object proceed = joinPoint.proceed(args);
        log.info("Around before");
        return proceed;
    }
}
