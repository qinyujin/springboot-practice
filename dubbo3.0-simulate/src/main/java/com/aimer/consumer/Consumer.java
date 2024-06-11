package com.aimer.consumer;

import com.aimer.framework.ProxyFactory;
import com.aimer.provider.service.HelloService;

/**
 * @Author:yujinqin
 * @Date:2023/1/19 15:53
 */
public class Consumer {
    public static void main(String[] args) {
        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        String result = helloService.sayHello("你好");
        System.out.println(result);
    }
}
