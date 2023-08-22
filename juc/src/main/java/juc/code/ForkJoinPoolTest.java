package juc.code;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * @author :覃玉锦
 * @create :2021-01-04 09:45:00
 * ForkJoin框架是一个分治的思想，可以把问题分解成小问题，最后把结果合在一起
 * 问题场景：多线程如果遇到其中某个线程有阻塞，则该线程不会继续执行，但是与此同时其他没有阻塞的线程正常执行完后闲置了，导致
 * cpu性能没有充分利用。使用这个框架可以让阻塞的线程从后往前把任务分配给空闲的线程，可以让cpu充分利用。可以打开任务管理器看cpu
 * 利用率
 */
public class ForkJoinPoolTest {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinSumCalculate(0L, 50000000000L);

        long start = System.currentTimeMillis();
        Long sum = pool.invoke(task);

        System.out.println(sum);
        long end = System.currentTimeMillis();

        System.out.println("耗费时间："+(end - start));
    }

    @Test
    public void test1(){
        long sum = 0;
        long start = System.currentTimeMillis();
        for (long i = 0; i <= 50000000000L; i++) {
            sum+=i;
        }
        System.out.println(sum);
        long end = System.currentTimeMillis();
        System.out.println("耗费时间:"+(end-start));
    }

    @Test
    public void test2(){
        long start = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(0L, 50000000000L)
                .parallel()
                .reduce(0L, Long::sum);
        System.out.println(sum);
        long end = System.currentTimeMillis();
        System.out.println("耗费时间："+(end - start));
    }
}

class ForkJoinSumCalculate extends RecursiveTask<Long> {
    private static final long serialVersionUID = -2126363442115163128L;

    private long start;
    private long end;
    //每次拆分多少
    private final long THRESHOLD = 1000000;

    public ForkJoinSumCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;
        //分到阈值则开始合
        if(length<=THRESHOLD){
            long sum = 0L;
            for (long i = start; i <= end; i++) {
                sum+=i;
            }
            return sum;
        }
        else {
            long mid = (start+end)/2;
            //进行拆分，同时压入线程队列，fork：拆，join：合
            ForkJoinSumCalculate left = new ForkJoinSumCalculate(start,mid);
            left.fork();

            ForkJoinSumCalculate right = new ForkJoinSumCalculate(mid+1,end);
            right.fork();

            return left.join() + right.join();
        }
    }
}
