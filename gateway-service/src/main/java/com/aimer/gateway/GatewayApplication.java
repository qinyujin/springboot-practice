package com.aimer.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author :覃玉锦
 * @create :2020-08-15 11:59:00
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.aimer.component","com.aimer.gateway"})
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}