package juc;

import java.util.concurrent.TimeUnit;

/**
 * @author :覃玉锦
 * @create :2021-02-10 22:34:00
 * ThreadLocal是什么。Thread类里有一个ThreadLocal.ThreadLocalMap。说明线程类里面是有key value结构的。ThreadLocal就
 * 相当于是一个线程的局部存储。应用场景有spring的事务，如果一个方法有事务注解，并且有很多内层方法，那么为了避免内层方法去连接池
 * 中再拿数据库连接，那么会采取ThreadLocal存储一个连接，这样内层方法都从ThreadLocal中取，只会取到一个。
 */
public class ThreadLocalDemo {
    private static ThreadLocal<Person> tl = new ThreadLocal<>();
    public static void main(String[] args) {

        //线程1可以get到对象，因为Thread1的key value存入了。
        new Thread(() ->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            tl.set(new Person("LiSi"));
            System.out.println(tl.get().name);
            //如果不remove掉，那么此时key由于是弱引用，在对象被回收的情况下，key也被回收了，成为null，因此无法通过key找到value
            //由于value此时指向的是person，是强引用，所以person对象回收不掉，因此会造成内存泄露。
            //tl.remove();
        }).start();

        //线程2不可以get到对象
        new Thread(() ->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(tl.get());
        }).start();
    }

    static class Person{
        public Person(String name) {
            this.name = name;
        }

        String name = "ZhangSan";
    }
}
