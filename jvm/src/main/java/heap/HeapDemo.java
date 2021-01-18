package heap;

/**
 * @author :覃玉锦
 * @create :2021-01-13 17:04:00
 * 堆的参数设置：
 * -Xms10m（初始堆空间） -Xmx10m（最大堆空间）
 * 可以通过jdk提供的软件jvisualvm来查看
 */
public class HeapDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("开始");
        Thread.sleep(1000000);
        System.out.println("结束");
    }
}
