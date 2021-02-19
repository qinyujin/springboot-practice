package juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author :覃玉锦
 * @create :2021-02-18 14:16:00
 * ArrayBlockingQueue、LinkedBlockingQueue、SynchronousQueue同步队列。
 */
public class BlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
//        normalQueue();
        synchronousQueue();
    }

    /**
     * 同步阻塞队列，put之后只能take，不能再put。相当于容量为1
     */
    private static void synchronousQueue() {
        //同步阻塞队列的特点是put之后必须take，不然就阻塞。也就是说容量为1.
        SynchronousQueue<String> queue = new SynchronousQueue<>();
        new Thread(()->{
            try {
                System.out.println("队列put A入队");
                queue.put("A");
                System.out.println("队列put B入队");
                queue.put("B");
                System.out.println("队列put C入队");
                queue.put("C");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        new Thread(() ->{
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+" "+queue.take());
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+" "+queue.take());
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+" "+queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();
    }

    /**
     * 普通阻塞队列方法
     */
    private static void normalQueue() {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
        //1、会抛异常的方法
        /*queue.add("a");
        queue.add("b");
        queue.add("c");
        //IllegalStateException: Queue full
        queue.add("x");*/

        /*System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        //NoSuchElementException
        System.out.println(queue.remove());*/

//        System.out.println(queue.element());

        //2、不会抛异常的方法
        /*System.out.println(queue.offer("a"));
        System.out.println(queue.offer("a"));
        System.out.println(queue.offer("a"));
        //false
        System.out.println(queue.offer("a"));*/

        /*System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        //null
        System.out.println(queue.poll());*/

        //null
//        System.out.println(queue.peek());

        //3、会阻塞的方法
        /*queue.put("a");
        queue.put("b");
        queue.put("c");
        queue.put("x");*/

        /*System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());*/

        //4、定时的方法，比较特殊，这里的时间只有在队列满了才去等待，前三个不用等待，第四个需要等待2秒，如果失败就false
        /*System.out.println(queue.offer("a", 2L, TimeUnit.SECONDS));
        System.out.println(queue.offer("b", 2L, TimeUnit.SECONDS));
        System.out.println(queue.offer("c", 2L, TimeUnit.SECONDS));
        //2s后false
        System.out.println(queue.offer("x", 2L, TimeUnit.SECONDS));*/

        /*System.out.println(queue.poll(2L, TimeUnit.SECONDS));
        System.out.println(queue.poll(2L, TimeUnit.SECONDS));
        System.out.println(queue.poll(2L, TimeUnit.SECONDS));
        //2s后null
        System.out.println(queue.poll(2L, TimeUnit.SECONDS));*/
    }
}
