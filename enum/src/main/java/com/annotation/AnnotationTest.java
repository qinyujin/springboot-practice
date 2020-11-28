package com.annotation;

import java.lang.annotation.Annotation;

/**
 * @author :覃玉锦
 * @create :2020-10-09 16:08:00
 */
public class AnnotationTest {
    public static void main(String[] args) {
        //通过反射获取注解
        Class<Person> clazz = Person.class;
        Annotation[] annotations = clazz.getAnnotations();

        System.out.println(annotations.length);
        for (int i = 0; i < annotations.length; i++) {
            System.out.println(annotations[i]);
        }
    }
}

