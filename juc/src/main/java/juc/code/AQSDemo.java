package juc.code;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author :覃玉锦
 * @create :2021-02-08 20:39:00
 * AQS全称AbstractQueuedSynchronizer抽象队列同步器。AQS=state+CLH队列
 */
public class AQSDemo {
    public static void main(String[] args) {
        //默认是非公平锁
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        //银行案例，假设有3个顾客，第一个需要办理20分钟，其他俩顾客在候客区等待（CLH队列）。
        new Thread(() ->{
            lock.lock();
            try {
                condition.await();
                System.out.println(Thread.currentThread().getName()+" come in");
                TimeUnit.MICROSECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"A").start();

        new Thread(() ->{
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+" come in");
            } finally {
                lock.unlock();
            }
        },"B").start();

        new Thread(() ->{
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+" come in");
            } finally {
                lock.unlock();
            }
        },"C").start();
    }
}
