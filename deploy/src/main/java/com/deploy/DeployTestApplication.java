package com.deploy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author :覃玉锦
 * @create :2020-10-01 15:11:00
 */
@SpringBootApplication
@RestController
@ComponentScan({"com.aimer.component","com.deploy"})
public class DeployTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(DeployTestApplication.class, args);
    }

    @GetMapping("hello")
    public Map hello(){
        return Map.of("欢迎","helloWorld!");
    }
}
