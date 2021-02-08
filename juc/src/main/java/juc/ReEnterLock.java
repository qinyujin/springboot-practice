package juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author :覃玉锦
 * @create :2021-02-08 09:28:00
 * 可重入锁又名递归锁是可以递归循环使用的锁。简单来说就是假设有外层、中层、内层的方法，那么当一个线程的外层获得锁时，它的
 * 中、内层不用等待锁释放就可以使用该锁。
 * <p>
 * ReentrantLock和synchronized都是可重入锁，可重入锁的一个优点是可一定程度避免死锁。
 * synchronized是隐式锁
 * Lock，即ReentrantLock是显式锁
 * <p>
 * 可重入锁的底层原理：字节码层面是moniterenter（加锁）和moniterexit（释放锁）。锁对象有一个计数器和一个线程指针
 * 当锁被线程获取时 计数器+1（相当于moniterenter） 当被线程释放时 计数器-1（相当于moniterexit）。那么递归锁就是
 * 查看该锁的计数器是否为1，如果为1并且线程指针是当前线程的话，那么就可以给计数器+1，然后继续使用。
 */
public class ReEnterLock {
    static Object lock1 = new Object();

    static Lock lock2 = new ReentrantLock();

    //1、synchronized代码块验证可重入锁
    public static void m1() {
        new Thread(() -> {
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + "===外层");
                //这时候外层方法还没执行结束，也就是说lock1并没有释放，但是内层方法还是可以获取该锁。
                synchronized (lock1) {
                    System.out.println(Thread.currentThread().getName() + "===内层");
                }
            }
        }, "t1").start();
    }

    //2、synchronized方法验证可重入锁
    public synchronized static void m2() {
        System.out.println(Thread.currentThread().getName() + "===外层");
        m3();
    }

    public synchronized static void m3() {
        System.out.println(Thread.currentThread().getName() + "===内层");
    }

    //3、Lock验证可重入锁，这里就可以看出可重入锁的底层原理，如果lock的数量多于unlock，计数器就大于0，导致其他线程
    //无法获得锁
    public static void m4() {
        new Thread(() -> {
            try {
                //如果lock和unlock数量不一样，那么其他线程无法获取该lock，但是该线程还是可以正常运行结束
                lock2.lock();
                lock2.lock();
                System.out.println(Thread.currentThread().getName() + "===外层");
                try {
                    lock2.lock();
                    System.out.println(Thread.currentThread().getName() + "===内层");
                } finally {
                    lock2.unlock();
                }
            } finally {
                lock2.unlock();
//                lock2.unlock();
            }
        }, "t2").start();

        new Thread(() -> {
            try {
                lock2.lock();
                System.out.println(Thread.currentThread().getName() + "其他线程");
            } finally {
                lock2.unlock();
            }
        }, "t3").start();
    }

    public static void main(String[] args) {
        m1();
        m2();
        m4();
    }
}
