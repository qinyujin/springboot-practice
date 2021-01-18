package create.single;

/**
 * @author :覃玉锦
 * @create :2021-01-18 09:52:00
 * 饿汉式单例模式，一开始就创建，在没有用到对象时会导致浪费空间。
 */
public class Hungry {
    private static final Hungry instance = new Hungry();
    private Hungry(){
        System.out.println(Thread.currentThread().getName()+"构造器");
    };

    public static Hungry getInstance() throws InterruptedException {
        Thread.sleep(200);
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                Hungry h = null;
                try {
                    System.out.println(Hungry.getInstance());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
