package com.aimer.component;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author :覃玉锦
 * @create :2020-08-11 19:29:00
 */
@SpringBootApplication
@MapperScan("com.aimer.component.dao")
public class CommonApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonApplication.class, args);
    }
}
