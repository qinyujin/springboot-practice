package juc.code;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author :覃玉锦
 * @create :2021-02-08 10:35:00
 */
public class LockSupportTest {
    static Object lock1 = new Object();

    public static void main(String[] args) throws Exception {
        method2();

    }

    //使用wait/notify或者是await/signal的限制是必须在锁块中，不然会有异常java.lang.IllegalMonitorStateException，并且有先后顺序，只能先wait。
    private static void method1(){
        new Thread(() ->{
            /*try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            synchronized (lock1){
                System.out.println(Thread.currentThread().getName() + "进入方法");
                try {
                    lock1.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("方法继续");
            }
        },"A").start();

        new Thread(() ->{
            synchronized (lock1){
                lock1.notify();
                System.out.println(Thread.currentThread().getName()+"唤醒");
            }
        },"B").start();
    }

    //LockSupport是基于许可证来实现的阻塞和唤醒。许可证只能为0或1，默认为0，park方法会判断许可证的值，为0阻塞。
    //unpark(Thread t) 需要使用线程作为参数，可以让该线程的许可证为1.调用park会把1变成0，相当于使用许可证。
    //示例方法就没有上面两种限制，即必须在锁块中和顺序限制
    public static void method2() {
        Thread A = new Thread(() ->{
            /*try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            System.out.println(Thread.currentThread().getName()+" come in");
            //一开始是阻塞的,直到unpark颁发许可证之后才消费许可证，但是只有一个许可证，所以第二个park还是阻塞
            LockSupport.park();
            LockSupport.park();
            System.out.println(Thread.currentThread().getName()+"被唤醒");
        },"A");
        A.start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread B = new Thread(() ->{
            //由于许可证最高只能为1，所以两个unpark还是只能让许可证为1
            LockSupport.unpark(A);
            LockSupport.unpark(A);
            System.out.println(Thread.currentThread().getName()+"通知");
        },"B");
        B.start();
    }

}
