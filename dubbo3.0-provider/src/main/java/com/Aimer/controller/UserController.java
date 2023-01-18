package com.Aimer.controller;

import com.Aimer.service.UserServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author:yujinqin
 * @Date:2023/1/18 14:40
 *
 * MVC层仅提供调用
 */
@RestController
public class UserController {
    @Resource
    private UserServiceImpl userService;

    @GetMapping("/user")
    public String getUser(){
        return userService.getUser();
    }
}
