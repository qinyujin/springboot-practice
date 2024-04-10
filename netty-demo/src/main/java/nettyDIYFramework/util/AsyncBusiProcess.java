package nettyDIYFramework.util;

import io.netty.util.NettyRuntime;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author :覃玉锦
 * @create :2024-04-08 20:19:00
 * 线程池处理异步业务
 */
public class AsyncBusiProcess {

    private static ExecutorService executorService = new ThreadPoolExecutor(1,
            NettyRuntime.availableProcessors(),
            60,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(3000));

    public static void submitTask(Runnable task) {
        executorService.execute(task);
    }
}
