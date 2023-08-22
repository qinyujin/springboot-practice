package juc.code;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author :覃玉锦
 * @create :2020-12-31 20:51:00
 * 读写锁的使用。
 * 对于两个线程来说
 * 读写/写写 需要互斥
 * 读读 不需要互斥
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {
        //一个写，一百个读
        ReadWriteLockDemo rw = new ReadWriteLockDemo();
            new Thread(new Runnable(){
                @Override
                public void run() {
                    rw.set((int)(Math.random()*101));
                }
            },"Write").start();

        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable(){
                @Override
                public void run() {
                    rw.get();
                }
            },"Read").start();
        }
    }
}

class ReadWriteLockDemo{
    private int number;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void get(){
        //读
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+" : "+number);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void set(int i){
        //写
        lock.writeLock().lock();
        try {
            number = i;
            System.out.println(Thread.currentThread().getName());
        } finally {
            lock.writeLock().unlock();
        }
    }
}
