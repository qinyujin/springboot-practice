package juc.code;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author :覃玉锦
 * @create :2020-12-29 22:34:00
 * 原子类的使用，可以保证原子性（同一个变量不会出现不同的修改）
 */
public class AtomicTest {
    public static void main(String[] args) {
        AtomicDemo atomicDemo = new AtomicDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(atomicDemo).start();
        }
    }
}

class AtomicDemo implements Runnable{
//    private volatile int serialNumber = 0;
    private AtomicInteger serialNumber = new AtomicInteger();

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getSerialNumber());
    }

    public int getSerialNumber() {
//        return serialNumber++;
        return serialNumber.getAndIncrement();
    }
}
