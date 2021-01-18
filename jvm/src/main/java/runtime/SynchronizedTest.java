package runtime;

/**
 * @author :覃玉锦
 * @create :2021-01-14 09:59:00
 * 如果在逃逸分析时发现方法的锁不会释放到方法外，那么就会把锁给优化掉
 */
public class SynchronizedTest {

    /**
     * 由于锁对象只存在于方法内，代码会优化
     */
    public void synchronizedMethod(){
        Object qyj = new Object();
        synchronized (qyj){
            System.out.println(qyj);
        }
    }

    /**
     * 优化后的代码
     */
    public void betterSynMethod(){
        Object qyj = new Object();
        System.out.println(qyj);
    }
}
