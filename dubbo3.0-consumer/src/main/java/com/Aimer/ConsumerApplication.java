package com.Aimer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Author:yujinqin
 * @Date:2023/1/18 14:26
 */
@SpringBootApplication
public class ConsumerApplication {
    //inject restTemplate bean
    @Bean
    public RestTemplate getTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class);
    }
}
