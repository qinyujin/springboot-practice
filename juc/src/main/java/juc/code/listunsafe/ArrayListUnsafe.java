package juc.code.listunsafe;

import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author :覃玉锦
 * @create :2021-02-18 11:45:00
 * arrayslist线程不安全的案例
 * 多线程情况下对arlist进行add会产生ConcurrentModificationException异常。
 * 解决办法：
 * 1、使用vector，但是效率低下，不建议
 * 2、使用工具类Collections.synchronizedList(List list) 来加锁
 * 3、使用写时复制类copyOnWriteArrayList();
 *
 */
public class ArrayListUnsafe {
    public static void main(String[] args) {
//        ArrayList<String> list = new ArrayList<>();
//        List<Object> list = Collections.synchronizedList(new ArrayList<>());
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            }).start();
        }
    }
}
