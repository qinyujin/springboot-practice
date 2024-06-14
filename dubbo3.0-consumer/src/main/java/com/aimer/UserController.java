package com.aimer;

import org.apache.dubbo.common.stream.StreamObserver;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @DubboReference
    private UserService userService;

    @GetMapping("/getUser")
    public User getUser() {
        User user = userService.getUser();
        String result = userService.getUser("yujin");
        System.out.println("str result:" + result);

        return user;
    }


    @GetMapping("/stream1")
    public void stream1() {
        userService.sayHelloServerStream("yujin", new StreamObserver<String>() {
            @Override
            public void onNext(String data) {
                //服务端返回
                System.out.println(data);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                System.out.println("completed");
            }
        });
    }

    @GetMapping("/stream2")
    public void stream2() {
        StreamObserver<String> streamObserver = userService.sayHelloClientStream(new StreamObserver<String>() {
            @Override
            public void onNext(String data) {
                System.out.println("接收响应数据");
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                System.out.println("接收响应数据完毕");
            }
        });

        streamObserver.onNext("request yujin hello");
        streamObserver.onNext("request yujin world");
        streamObserver.onCompleted();
    }
}
