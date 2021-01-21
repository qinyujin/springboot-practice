package create.single;

/**
 * @author :覃玉锦
 * @create :2021-01-19 11:01:00
 */
public class EnumTest {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() ->{
                System.out.println(Singleton.INSTANCE);
            }).start();
        }
    }
}

enum Singleton{
    INSTANCE;

    Singleton() {
        System.out.println(Thread.currentThread().getName()+"枚举构造");
    }

    public void ok(){
        System.out.println("枚举是单例的");
    }
}
