package com.aimer.exception.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author :覃玉锦
 * @create :2020-09-17 19:33:00
 */
@Service
public class ThrowResponseException {

    public void test(){
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"服务层抛出的异常");
    }
}
