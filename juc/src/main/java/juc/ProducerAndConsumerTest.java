package juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author :覃玉锦
 * @create :2020-12-30 13:27:00
 * 使用lock来实现生产者与消费者问题
 * 生产者与消费者存在的问题：
 * 1、如果不使用wait、notify等，会导致不断生产（即使已满）或不断消费
 * 2、需要注意是否出现未唤醒的情况
 * 3、考虑多个线程是否会导致把其他线程给唤醒
 */
public class ProducerAndConsumerTest {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Producer p = new Producer(clerk);
        Consumer c = new Consumer(clerk);

        Producer p1 = new Producer(clerk);
        Consumer c1 = new Consumer(clerk);


        //消费者、生产者同时进行
        new Thread(p, "生产者").start();
        new Thread(c, "消费者").start();
        new Thread(p1,"生产者1").start();
        new Thread(c1, "消费者1").start();
    }
}

class Clerk {
    private int product = 0;
    private Lock lock = new ReentrantLock();
    //如果要使用lock来锁，那么原来的wait、notify、notifyall 变成 await、signal、signalAll等
    private Condition condition = lock.newCondition();

    public /*synchronized*/ void producer() {
        lock.lock();
        try {
            while (product >= 10) {
                System.out.println("已满不能生产");
                //等待
                try {
//                    this.wait();
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("生产" + ++product);
//            this.notifyAll();
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public /*synchronized*/ void consumer() {
        lock.lock();
        try {
            while (product <= 0) {
                System.out.println("为空不能消费");
                try {
//                    this.wait();
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("消费" + product--);
//            this.notifyAll();
            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }
}

class Producer implements Runnable {
    private Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        //
        for (int i = 0; i < 20; i++) {
            clerk.producer();
        }
    }
}

class Consumer implements Runnable {
    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        //消费20个
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.consumer();
        }
    }
}
