package com.aimer.service;

import com.aimer.UserService;
import org.apache.dubbo.common.stream.StreamObserver;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Author:yujinqin
 * @Date:2023/1/18 14:39
 * <p>
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
        //http
        /*String forObject = restTemplate.getForObject("http://localhost:8080/user", String.class);
        return forObject;*/

        //unary
//        return userService.getUser();

        //server_stream
        /*userService.sayHelloServerStream("hi", new StreamObserver<String>() {
            @Override
            public void onNext(String data) {
                System.out.println("receive result:" + data);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                System.out.println("deal done!");
            }

        });
        return "success";*/

        //client_stream
        StreamObserver<String> response = userService.sayHelloClientStream(new StreamObserver<String>() {
            @Override
            public void onNext(String data) {
                System.out.println("client receives stream data:" + data);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                System.out.println("client processed done");
            }
        });

        response.onNext("1");
        response.onNext("2");
        response.onNext("3");
        response.onCompleted();

        return "success";
    }
}
