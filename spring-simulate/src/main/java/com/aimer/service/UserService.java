package com.aimer.service;

import com.spring.*;

/**
 * @author :覃玉锦
 * @create :2023-04-24 16:12:00
 */
@Component("userService")
@Scope("singleton")
public class UserService implements InitializingBean, UserInterface {
    @Autowired
    private OrderService orderService;

    //把值初始化为xxx，也可以通过postProcessor
    @MyValue("xxx")
    private String name;

    public void test() {
        System.out.println(orderService);
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("初始化" + name);
    }
}
