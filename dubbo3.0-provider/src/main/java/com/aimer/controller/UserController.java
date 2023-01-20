package com.aimer.controller;

import com.aimer.proto.UserRequest;
import com.aimer.service.UserServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author:yujinqin
 * @Date:2023/1/18 14:40
 * <p>
 * MVC层仅提供调用
 */
@RestController
public class UserController {
    @Resource
    private UserServiceImpl userService;

    @GetMapping("/user")
    public String getUser() {
        //common module method
//        return userService.getUser();

        //protobuf method
        return userService.getUser(UserRequest.newBuilder().setUid("111").build()).getUserName();
    }
}
