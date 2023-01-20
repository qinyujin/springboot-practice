package com.aimer.provider.service.impl;

import com.aimer.provider.service.HelloService;

/**
 * @Author:yujinqin
 * @Date:2023/1/19 15:54
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String userName) {
        return "hi:" + userName;
    }
}
