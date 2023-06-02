package com.aimer.service;

import com.spring.BeanPostProcessor;
import com.spring.Component;
import com.spring.MyValue;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author :覃玉锦
 * @create :2023-04-24 21:36:00
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        //处理自定义注解
        for (Field field : bean.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(MyValue.class)) {
                MyValue annotation = field.getAnnotation(MyValue.class);

                field.setAccessible(true);
                try {
                    field.set(bean, annotation.value());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        //这种方式默认是针对所有bean的，可以通过beanName来特例判断

        if (beanName.equals("userService")) {
            System.out.println("bean post 初始化" + beanName);

            Object proxyInstance = Proxy.newProxyInstance(MyBeanPostProcessor.class.getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("切面");

                    return method.invoke(bean, args);
                }

            });

            return proxyInstance;
        }

        return bean;
    }
}
