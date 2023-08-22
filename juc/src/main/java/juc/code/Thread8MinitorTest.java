package juc.code;

/**
 * @author :覃玉锦
 * @create :2020-12-31 21:08:00
 * 线程8锁，在使用多线程时最常见到的八种情况：
 * 注意一个原则，非静态同步方法的锁是this，静态同步方法的锁是当前类的Class实例
 *
 * 1、两个普通同步方法，两个线程，标准打印，打印?   //one two
 * 2、在getOne方法里增加Thread.Sleep()，打印?   //one two
 * 3、新增普通方法getThree，打印?   //three one two
 * 4、两个普通同步方法，两个对象，打印？  //two one
 * 5、把getOne改成静态方法，打印？     //two one
 * 6、修改两个都为静态同步方法，一个Number对象，打印？ //one two
 * 7、一个静态同步方法，一个非静态同步方法，两个对象  //two one
 * 8、两个静态同步方法，两个对象       //one two
 */
public class Thread8MinitorTest {
    public static void main(String[] args) {
        Number tm = new Number();
        Number tm2 = new Number();
        new Thread(new Runnable(){
            @Override
            public void run() {
                tm.getOne();
            }
        }).start();

        new Thread(new Runnable(){
            @Override
            public void run() {
//                tm.getTwo();
                tm2.getTwo();
            }
        }).start();

        new Thread(new Runnable(){
            @Override
            public void run() {
//                tm.getThree();
            }
        }).start();
    }
}

class Number {
    public static synchronized void getOne(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("one");
    }

    public static synchronized void getTwo(){
        System.out.println("two");
    }

    public void getThree(){
        System.out.println("three");
    }
}
