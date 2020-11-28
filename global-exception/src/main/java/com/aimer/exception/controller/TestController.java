package com.aimer.exception.controller;

import com.aimer.exception.service.ThrowResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

/**
 * @author :覃玉锦
 * @create :2020-09-17 17:25:00
 */
@RestController
public class TestController {

    @Autowired
    private ThrowResponseException throwResponseException;

    @GetMapping("test")
    public Map test(){
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"未授权");
    }

    @GetMapping("test1")
    public Map test1(){
        throwResponseException.test();
        return Map.of("yes","yes");
    }
}
