package com.Aimer.service;

import com.Aimer.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Author:yujinqin
 * @Date:2023/1/18 14:39
 *
 * dubbo 调用的是common模块中的接口，该接口注册在zookeeper上，同时调用也通过zookeeper去找
 */
@Service
public class OrderService {
    @Resource
    private RestTemplate restTemplate;

    //引用dubbo服务，可通过version属性引用不同实现
    @DubboReference(version = "1.0")
    private UserService userService;

    //传统形式通过http调用
    public String getOrder() {
        //method1
        /*String forObject = restTemplate.getForObject("http://localhost:8080/user", String.class);
        return forObject;*/

        //method2
        return userService.getUser();
    }
}
