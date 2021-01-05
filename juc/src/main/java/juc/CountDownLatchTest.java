package juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author :覃玉锦
 * @create :2020-12-29 23:41:00
 * 闭锁，当一定数量的任务执行完后才继续执行
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(5);
        CountDownLatchDemo countDownLatchDemo = new CountDownLatchDemo(latch);
        long begin = System.currentTimeMillis();
        //5个线程都countDown之后，latch的wait就会执行
        for (int i = 0; i < 5; i++) {
            new Thread(countDownLatchDemo).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗费时间:"+(end - begin));
    }
}

class CountDownLatchDemo implements Runnable{
    //执行countDown，count就-1，当countDown为0，则await开始执行
    private CountDownLatch countDownLatch;

    public CountDownLatchDemo(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        synchronized (this){
            try {
                for (int i = 0; i < 50000; i++) {
                    if(i%2==0){
                        System.out.println(i);
                    }
                }
            } finally {
                countDownLatch.countDown();
            }
        }
    }
}