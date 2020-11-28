package com.aimer.lock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author :覃玉锦
 * @create :2020-09-21 11:17:00
 */
@SpringBootApplication
@MapperScan(value = "com.aimer.lock.mapper")
public class OptimisticApplication {
    public static void main(String[] args) {
        SpringApplication.run(OptimisticApplication.class, args);
    }
}
