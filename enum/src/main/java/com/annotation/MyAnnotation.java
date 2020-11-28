package com.annotation;

import java.lang.annotation.*;

/**
 * @author :覃玉锦
 * @create :2020-10-09 16:09:00
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.FIELD,ElementType.METHOD})
public @interface MyAnnotation {
    String value() default "hello";
}
