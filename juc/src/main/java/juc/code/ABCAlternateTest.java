package juc.code;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author :覃玉锦
 * @create :2020-12-31 20:30:00
 * 题目：线程按序打印，按照ABCABC。。。的顺序打印
 */
public class ABCAlternateTest {
    public static void main(String[] args) {
        //开启3个线程同时跑起来
        ABCAlterDemo ad = new ABCAlterDemo();

        new Thread(new Runnable(){
            @Override
            public void run() {
                for (int i = 1; i <= 20; i++) {
                    ad.printA(i);
                }
            }
        },"A").start();

        new Thread(new Runnable(){
            @Override
            public void run() {
                for (int i = 1; i <= 20; i++) {
                    ad.printB(i);
                }
            }
        },"B").start();

        new Thread(new Runnable(){
            @Override
            public void run() {
                for (int i = 1; i <= 20; i++) {
                    ad.printC(i);
                }
            }
        },"C").start();
    }
}

class ABCAlterDemo{
    //编号代表打印哪个字母
    private int num = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void printA(int count){
        lock.lock();
        try {
            if(num!=1){
                //如果当前没到第一个线程打印，让第一个线程等待
                condition1.await();
            }
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName()+":"+i+" "+count);
            }
            //第一个线程打印5次后唤醒线程2
            num = 2;
            condition2.signal();
        } catch (Exception e){
          e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void printB(int count){
        lock.lock();
        try {
            if(num!=2){
                //如果当前没到第一个线程打印，让第一个线程等待
                condition2.await();
            }
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName()+":"+i+" "+count);
            }
            //第一个线程打印5次后唤醒线程2
            num = 3;
            condition3.signal();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void printC(int count){
        lock.lock();
        try {
            if(num!=3){
                //如果当前没到第一个线程打印，让第一个线程等待
                condition3.await();
            }
            for (int i = 1; i <= 20; i++) {
                System.out.println(Thread.currentThread().getName()+":"+i+" "+count);
            }
            //第一个线程打印5次后唤醒线程2
            num = 1;
            condition1.signal();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
