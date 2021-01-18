package create.single;

/**
 * @author :覃玉锦
 * @create :2021-01-18 10:12:00
 * 懒汉式单例模式。可能会有线程安全问题。
 */
public class Lazy {
    private Lazy(){
        System.out.println(Thread.currentThread().getName()+"lazy 构造");
    };
    private static Lazy instance;

    /**
     * 不做任何处理，有线程安全问题
     * @return
     */
    public static  Lazy getInstance() {
        if(instance==null){
            instance = new Lazy();
        }

        //改法:在new的时候同步
        /*if(instance==null){
            synchronized (Lazy.class){
                if(instance==null){
                    instance = new Lazy();
                    *//*1、分配内存空间
                    * 2、执行构造方法初始化对象
                    * 3、把对象指向这个空间
                    *
                    * 过程中有可能发生指令重排：
                    * 123->132
                    * 有可能对象初始化完了但还没有指向空间，如果另一个线程来指向这个空间，会导致安全问题
                    * 解决办法就是给instance变量加上volatile
                    * *//*
                }
            }
        }*/
        //可能会由于指令重排发生错误，加上volatile
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                    Lazy.getInstance();
            }).start();
        }
    }
}
