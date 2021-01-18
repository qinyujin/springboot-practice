package classloader;

/**
 * @author :覃玉锦
 * @create :2021-01-08 19:44:00
 */
public class ClInitTest {
    public static void main(String[] args) {
        /*Runnable r = () -> {
            System.out.println(Thread.currentThread().getName() + "开始");
            new DeadThread();
            System.out.println(Thread.currentThread().getName() + "结束");
        };
        new Thread(r, "线程A").start();
        new Thread(r, "线程B").start();*/
    }
}

class DeadThread {
    static {
        if (true) {
            System.out.println(Thread.currentThread().getName() + "静态代码块执行");
            while (true) {

            }
        }
    }
}