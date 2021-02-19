package juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author :覃玉锦
 * @create :2021-02-18 13:16:00
 * 这个和闭锁相反，闭锁是减到0才开始，这个是运行到数量才执行实现的方法。
 * 构造器两个参数，第一个是数量，第二个是函数式接口，用来完成操作，当调用await方法，数量达到后，才会执行方法
 * 参考七龙珠，收集齐七颗龙珠才可召唤神龙
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        //只有7个await都执行完毕才会输出。
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("龙珠集齐，召唤神龙");
        });
        for (int i = 1; i <= 7; i++) {
            int flag = i;
            new Thread(() ->{
                try {
                    System.out.println("找到了第"+flag+"颗龙珠");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
