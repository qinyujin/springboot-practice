package juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author :覃玉锦
 * @create :2020-12-30 11:02:00
 * 使用callable创建线程的区别：1、有返回值 2、可以抛出异常
 * 使用它也可以实现闭锁的效果。调用FutureTask实例的get方法，没有get到的时候，当前线程是不会执行的
 */
public class CallableTest {
    public static void main(String[] args) {
        //1、有callable实例 2、有futuretask实例 3、线程实例
        CallableDemo call = new CallableDemo();
        FutureTask ft = new FutureTask(call);
        new  Thread(ft).start();

        Object o = null;
        try {
            o = ft.get();
            //必须等到get完才会执行输出，起到闭锁的作用
            System.out.println("--------------------------------------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class CallableDemo implements Callable {
    @Override
    public Object call() throws Exception {
        int i;
        for (i = 0; i < 100000; i++) {
            System.out.println(i);
        }
        return i;
    }
}