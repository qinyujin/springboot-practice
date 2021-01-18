package runtime;

/**
 * @author :覃玉锦
 * @create :2021-01-13 20:35:00
 * 新生代和老年代参数设置
 * 参数设置：-Xms600m -Xmx600m
 *
 * 新生代和老年代默认比例为1：2 可以通过-XX:NewRatio=ratio来设置，比如设置为1。
 * Eden区和survivor区的比例默认为6：1：1，InitialSurvivorRatio的默认值为8 可以通过-XX:InitialSurvivorRatio=ratio来设置，比如设置为4则为
 * 2:1:1
 *
 */
public class EdenSurvivorTest {
        public static void main(String[] args) throws InterruptedException {
        System.out.println("程序开始");
        Thread.sleep(1000000);
    }
}
