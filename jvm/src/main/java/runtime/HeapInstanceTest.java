package runtime;

import java.util.Random;

/**
 * @author :覃玉锦
 * @create :2021-01-13 21:29:00
 * 通过jvisual软件来查看新生代（Eden、s0、s1）和老年代之间的变化
 */
public class HeapInstanceTest {
    byte[] buffer = new byte[new Random().nextInt(1024*1024)];

    public static void main(String[] args) throws InterruptedException {
        /*List<HeapInstanceTest> list = new ArrayList<>();
        while (true){
            list.add(new HeapInstanceTest());
            Thread.sleep(10);
        }*/
    }
}
