package juc.code.listunsafe;

import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author :覃玉锦
 * @create :2021-02-18 12:12:00
 * HashSet的不安全问题。解决方法参考ArrayList
 * 1、工具类Collections
 * 2、CopyOnWriteArraySet
 *
 * CopyOnWriteArraySet的底层原理是使用的CopyOnWriteArrayList
 *
 */
public class HashSetUnsafe {
    public static void main(String[] args) {
//        HashSet<String> set = new HashSet<>();
//        Set<String> set = Collections.synchronizedSet(new HashSet<String>());
        CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() ->{
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            }).start();
        }


        //HashSet的底层是HashMap，那么这里add为啥只放一个值，如果是key，那么value是什么?value是PRESENT，一个常量
        //类型是new Object()
        HashSet<Integer> integers = new HashSet<>();
        integers.add(1);
    }
}
