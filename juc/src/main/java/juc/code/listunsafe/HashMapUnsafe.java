package juc.code.listunsafe;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author :覃玉锦
 * @create :2021-02-18 12:24:00
 * hashMap的解决方法：
 * 1、工具类Collections
 * 2、使用ConcurrentHashMap
 */
public class HashMapUnsafe {
    public static void main(String[] args) {
        //ConcurrentModificationException
//        HashMap<String, String> map = new HashMap<>();
//        Map<String, String> map = Collections.synchronizedMap(new HashMap<String, String>());
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() ->{
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },i+"").start();
        }
    }
}
