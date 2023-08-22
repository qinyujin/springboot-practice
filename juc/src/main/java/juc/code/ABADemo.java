package juc.code;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author :覃玉锦
 * @create :2021-02-08 17:27:00
 * ABA问题：CAS的情况下，当前值为1，线程1想改成10，但在线程1之前线程2把值改成2，又改回1，此时线程1比较发现值还是1，
 * 于是修改成功。但是中间值2的改动被忽略了。
 * 解决办法：采用一个版本号记录，每次修改都顺带修改版本号，那么比较的时候比较版本号和原始的值即可。可以使用时间戳原子
 * 引用类来实现：AtomicStampedReference<>
 */
public class ABADemo {
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    //版本号从1开始
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100,1);

    public static void main(String[] args) {
        //ABA问题,t1修改完了，t2还能修改
        new Thread(() ->{
            System.out.println(Thread.currentThread().getName()+":"+atomicReference.compareAndSet(100, 101));
            System.out.println(Thread.currentThread().getName()+":"+atomicReference.compareAndSet(101, 100));
        },"t1").start();

        new Thread(() ->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+":"+atomicReference.compareAndSet(100, 2020));
        },"t2").start();

        System.out.println("使用时间戳原子引用类来解决ABA问题");
        new Thread(() ->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+":"+atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
            System.out.println(Thread.currentThread().getName()+":"+atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
        },"t3").start();

        new Thread(() ->{
            int stamp = atomicStampedReference.getStamp();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+":"+atomicStampedReference.compareAndSet(100, 2020, stamp, stamp + 1));
        },"t4").start();
    }
}
