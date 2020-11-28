package com.annotation;

/**
 * @author :覃玉锦
 * @create :2020-10-09 19:45:00
 */
@MyTiger(value = "人，不是老虎")
public class Person {
    private String name;
    private int age;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
