package juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author :覃玉锦
 * @create :2020-12-30 12:15:00
 * 解决多线程安全问题的方式:
 * synchronized
 *  1、同步代码块
 *  2、同步方法
 * lock
 * 需要使用lock手动上锁，unlock手动解锁
 */
public class LockTest {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(ticket,"1号窗口").start();
        new Thread(ticket,"2号窗口").start();
        new Thread(ticket,"3号窗口").start();
    }
}

class Ticket implements Runnable{
    private int tick = 100;
    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true){
            lock.lock();
            try {
                if(tick > 0){
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"售出第"+(--tick)+"张票");
                }
                else {
                    break;
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
