package com.aop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author :覃玉锦
 * @create :2020-10-13 11:03:00
 */
@Service
@Slf4j
public class AopService {
    public String aopTest(int x){
        log.info("AopService");
        if(x==0)return "hello world!";
        else {
            throw new RuntimeException("八伯怎么说");
        }
    }
}
