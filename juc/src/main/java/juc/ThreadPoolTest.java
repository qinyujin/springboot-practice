package juc;

import java.util.concurrent.*;

/**
 * @author :覃玉锦
 * @create :2020-12-31 21:40:00
 * 一：线程池的使用，单个线程的使用存在的问题，就是一个线程使用了一次之后就销毁了，如果需要多次使用这个线程，就会造成重复的创建
 * 和销毁线程，比较耗费时间和效率。使用线程池就不会立即销毁线程，而是把不用的线程标记为可用状态。对于多次的线程使用，可以节省
 * 开销。
 *
 * 二：线程池的体系结构：
 *  Java.util.concurrent.Executor : 负责线程的使用和调度的根接口
 *       |--**ExecutorService 子接口: 线程池的主要接口
 *          |--ThreadPoolExecutor 线程池的实现类
 *          |--ScheduledExecutorService 子接口:负责线程的调度
 *             |--ScheduledThreadPoolExecutor :继承ThreadPoolExecutor，实现了ScheduledExecutorService
 *
 * 三：工具类：Executors
 * ExecutorService newFixedThreadPool(): 创建一个固定大小的线程池
 * ExecutorService newCachedThreadPool(): 缓存线程池，线程池的数量不固定，可以根据需求自动的更改数量
 * ExecutorService newSingleThreadPool(): 创建单个线程，线程池中只有一个线程
 *
 * ScheduledExecutorService newScheduledThreadPool(): 创建固定大小的线程，可以延时或定时的执行任务
 */
public class ThreadPoolTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //通过线程池来调用线程的操作
        //1、创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(5);
        ThreadPoolDemo thread = new ThreadPoolDemo();
        //2、提交任务
          //2.1 参数为runnable
        /*for (int i = 0; i < 5; i++) {
            pool.submit(thread);
        }*/
          //2.2  参数为callable
        Future<Integer> sum = pool.submit(new Callable<Integer>() {
            int sum = 0;

            @Override
            public Integer call() throws Exception {
                for (int i = 0; i <= 100; i++) {
                    sum += i;
                }
                return sum;
            }
        });
        System.out.println(sum.get());
        //3、关闭线程池，两种方式shutdown 和 shutdownNow，前者会把当前线程任务执行完，且不接受新任务
        pool.shutdown();


        //通过参数的方式创建线程池：参数：核心线程数、最大线程数、非核心线程存活时间、时间单位、等待队列、线程池工厂、拒绝策略
        //拒绝策略有四种，abort丢弃+抛异常，discard丢弃没有异常，CallerRun交给调用线程池的线程去执行，如这里就是main线程
        //执行，DiscardOldest把最早进入队列的线程移除，换成新的。
        ExecutorService executor = new ThreadPoolExecutor(3, 5, 2L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        for (int i = 0; i < 10; i++) {
            executor.execute(()->{
                //6个以及之前时只有3个线程工作，因为超过3个在等待队列里。7个时有4个线程，8个5线程。9个执行拒绝策略，
                //抛出异常
                System.out.println(Thread.currentThread().getName() + "工作");
            });
        }
        executor.shutdown();
    }
}

class ThreadPoolDemo implements Runnable{
    @Override
    public void run() {
        int i = 0;
        while (i<= 100){
            System.out.println(Thread.currentThread().getName() + " : " + i++);
        }
    }
}
