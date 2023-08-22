package juc.code;


import lombok.Data;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author :覃玉锦
 * @create :2021-02-08 17:10:00
 * 原子引用类：可以自定义原子类型，不再局限于什么integer、double。
 * 通过AtomicReference<> 来使用，主要方法有set get compareAndSet 等
 *
 * 时间戳原子引用：
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        AtomicReference<User> atomicReference = new AtomicReference<>();
        User u1 = new User("张三",18);
        User u2 = new User("李四",20);

        atomicReference.set(u1);
        System.out.println(atomicReference.compareAndSet(u1, u2) + " " + atomicReference.get());
        System.out.println(atomicReference.compareAndSet(u1, u2) + " " + atomicReference.get());
    }
}

@Data
class User{
    String name;
    int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
