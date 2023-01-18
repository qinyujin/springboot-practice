package com.Aimer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Aimer.service.OrderService;

import javax.annotation.Resource;

/**
 * @Author:yujinqin
 * @Date:2023/1/18 14:40
 */
@RestController
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping("/order")
    public String getOrder() {
        return orderService.getOrder();
    }
}
