package juc.code;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author :覃玉锦
 * @create :2021-01-04 09:23:00
 * 线程的调度（通过线程池方式）
 */
public class ScheduledThreadPoolTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //可以定时调度的线程池
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);
        //三个参数，callable接口、定时、时间单位
        for (int i = 0; i < 5; i++) {
            Future<Integer> future = pool.schedule(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int num = new Random().nextInt(100);
                    System.out.println(Thread.currentThread().getName() + " : " + num);
                    return num;
                }
            }, 1, TimeUnit.SECONDS);
            System.out.println(future.get());
        }

        pool.shutdown();
    }
}
