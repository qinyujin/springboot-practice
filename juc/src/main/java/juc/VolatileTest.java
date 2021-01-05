package juc;

/**
 * @author :覃玉锦
 * @create :2020-12-29 21:30:00
 */
public class VolatileTest {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        new Thread(thread).start();
        while (true) {
            if (thread.isFlag()) {
                System.out.println("----------------------");
                break;
            }
        }
    }
}

class MyThread implements Runnable {
    //volatile关键字修饰的变量可以让每个线程读取变量都是在主存而不是缓存中读取
    //比synchronized 稍微性能好一点，但是有两点：
    //1、没有互斥的特点
    //2、不能保证变量原子性
    volatile boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.flag = true;
        System.out.println(this.flag);
    }

    public boolean isFlag() {
        return this.flag;
    }

    @Override
    public String toString() {
        return "MyThread{" +
                "flag=" + flag +
                '}';
    }
}