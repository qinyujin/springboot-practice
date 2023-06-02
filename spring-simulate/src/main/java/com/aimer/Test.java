package com.aimer;

import com.aimer.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author :覃玉锦
 * @create :2023-04-24 16:11:00
 */
public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = (UserService) context.getBean("userService");

        userService.test();
        context.refresh();

        //创建单例的bean-->
        /*MyApplicationContext myApplicationContext = new MyApplicationContext(AppConfig.class);

        UserInterface userService = (UserInterface) myApplicationContext.getBean("userService");
        userService.test();*/
    }
}
