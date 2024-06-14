package com.aimer.service;

//使用的是common模块中的类
//import com.aimer.UserService;

//com.aimer.proto 则是通过protobuf定义的接口类

import com.aimer.User;
import com.aimer.UserService;
import org.apache.dubbo.common.stream.StreamObserver;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @Author:yujinqin
 * @Date:2023/1/18 14:39
 * Service层面提供服务处理逻辑
 * @DubboService 标记为一个dubbo服务
 */
//@Service
@DubboService
public class UserServiceImpl implements UserService {

    @Override
    public User getUser() {
        return new User("1", "yujin");
    }

    @Override
    public String getUser(String username) {
        return "getUser:" + username;
    }

    @Override
    public void sayHelloServerStream(String name, StreamObserver<String> response) {
        response.onNext(name + " hello");
        response.onNext(name + " world");
        response.onCompleted();
    }

    @Override
    public StreamObserver<String> sayHelloClientStream(StreamObserver<String> response) {
        return new StreamObserver<String>() {
            @Override
            public void onNext(String data) {
                //接收客户端传输的数据，返回一些信息回去
                response.onNext("result:" + data);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                System.out.println("completed");
            }
        };
    }
}
