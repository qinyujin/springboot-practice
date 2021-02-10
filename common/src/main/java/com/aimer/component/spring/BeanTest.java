package com.aimer.component.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author :覃玉锦
 * @create :2021-02-10 19:58:00
 * 测试一下ioc的底层原理实现，即使用工厂创建容器和使用容器创建对象
 */
public class BeanTest {
    public static void main(String[] args) {
        BeanFactory context = new ClassPathXmlApplicationContext("bean.xml");
        BeanTest user = (BeanTest) context.getBean("user");
        user.hello();
    }

    public void hello(){
        System.out.println("使用ApplicationContext创建的容器对象，解析了bean之后，通过getBean方法来获取对象。");
    }
}
