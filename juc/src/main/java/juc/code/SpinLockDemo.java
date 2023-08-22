package juc.code;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author :覃玉锦
 * @create :2021-02-08 19:42:00
 * 自旋锁：线程获取锁时不阻塞，而是选择循环尝试获取锁。
 *
 * 手写自旋锁：通过CAS操作完成自旋锁，A线程先进来调用mylock方法自己持有锁5秒钟，B随后进来发现当前线程持有锁，不是null，所以只能
 * 通过自旋等待，直到A释放锁后B随后抢到。
 */
public class SpinLockDemo {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    private void mylock(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName()+" come in 😀");
        //如果线程不为空则需要循环等待
        while (!atomicReference.compareAndSet(null,thread));
    }

    private void myunlock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
    }

    public static void main(String[] args) throws InterruptedException {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(() ->{
            spinLockDemo.mylock();
            System.out.println(Thread.currentThread().getName()+"获取到锁");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myunlock();
            System.out.println(Thread.currentThread().getName()+"释放锁");
        },"AA").start();

        //保证线程A先获取到锁
        TimeUnit.SECONDS.sleep(1);

        new Thread(() ->{
            spinLockDemo.mylock();
            System.out.println(Thread.currentThread().getName()+"获取到锁");
            spinLockDemo.myunlock();
        },"BB").start();
    }
}
