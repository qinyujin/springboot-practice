package create.single;

/**
 * @author :覃玉锦
 * @create :2021-01-18 09:52:00
 * 饿汉式单例模式，一开始就创建，在没有用到对象时会导致浪费空间。
 */
public class Hungry {
    /*============方式一，静态变量============*/
    /*//构造器私有
    private Hungry(){
        System.out.println(Thread.currentThread().getName()+"构造器");
    };
    //内部创建对象
    private static final Hungry instance = new Hungry();
    //提供唯一获取对象方法
    public static Hungry getInstance() throws InterruptedException {
        Thread.sleep(200);
        return instance;
    }*/

    /*============方式二，静态代码块============*/
    private Hungry(){
        System.out.println(Thread.currentThread().getName()+"构造器");
    }

    private static final Hungry instance;

    static {
        instance = new Hungry();
    }

    public static Hungry getInstance() throws InterruptedException {
        Thread.sleep(200);
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                Hungry h = null;
                try {
                    Hungry.getInstance();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
