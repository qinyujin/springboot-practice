package com.aimer.service;

//使用的是common模块中的类
//import com.aimer.UserService;

//com.aimer.proto 则是通过protobuf定义的接口类

import org.apache.dubbo.config.annotation.DubboService;

/**
 * @Author:yujinqin
 * @Date:2023/1/18 14:39
 * <p>
 * Service层面提供服务处理逻辑
 * @DubboService 才能使用dubbo查询到
 * <p>
 * tiple的流式通信通常有三种：unary、server-stream、client-stream
 */
//@Service
@DubboService(version = "1.0")
public class UserServiceImpl implements com.aimer.proto.UserService {

    //common module
    /*public String getUser() {
        return "Aimer";
    }

    @Override
    public void sayHelloServerStream(String name, StreamObserver<String> response) {
        //流式处理，可以处理多次
        response.onNext("hello:" + name);
        response.onNext("hello:" + name);
        response.onCompleted();
    }

    @Override
    public StreamObserver<String> sayHelloClientStream(StreamObserver<String> response) {
        return new StreamObserver<String>() {
            @Override
            public void onNext(String data) {
                System.out.println("the servers receives data:" + data);

                response.onNext("response result:" + data);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                System.out.println("processed done");
            }
        };
    }*/

    //protobuf provide interface list. refer to main/java/proto/userservice.proto
    @Override
    public com.aimer.proto.User getUser(com.aimer.proto.UserRequest request) {
        return com.aimer.proto.User.newBuilder().setUid(request.getUid()).setUserName("aimer").build();
    }
}
