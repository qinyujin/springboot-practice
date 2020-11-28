package com.aimer.loginservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author :覃玉锦
 * @create :2020-08-11 15:59:00
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.aimer"})
public class LoginApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoginApplication.class, args);
    }
}
