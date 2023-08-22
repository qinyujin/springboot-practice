package juc.code.concurrent_feature_jmm;

/**
 * @author :覃玉锦
 * @create :2023-08-02 23:01:00
 * 有序性问题。volatile保证有序性可以解决
 */
public class OrderlyTest {
    private static OrderlyTest instance;

    private OrderlyTest(){

    }

    public OrderlyTest getInstance(){
        if(instance == null){
            synchronized (OrderlyTest.class){
                if(instance == null){
                    /**
                     * 这里实际上有三步指令。
                     * 1、分配内存空间
                     * 2、初始化对象
                     * 3、把对象地址指向对应内存
                     *
                     * 如果发生cpu指令集重排序的话，可能的执行顺序就变成1、3、2.如果这样的话，在初始化完成之前，有其他线程来获取
                     * 实例，会视作对象已经创建完成从而获取到对象，但实际上对象还没有初始化完成。
                     */
                    instance = new OrderlyTest();
                }
            }
        }

        return instance;
    }
}
