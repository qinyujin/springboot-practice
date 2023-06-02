package com.spring;

/**
 * @author :覃玉锦
 * @create :2023-04-24 21:28:00
 */
public interface BeanPostProcessor {

    default Object postProcessBeforeInitialization(Object bean,String beanName){
        return bean;
    }

    default Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }
}
