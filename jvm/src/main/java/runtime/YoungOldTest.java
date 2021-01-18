package runtime;

/**
 * @author :覃玉锦
 * @create :2021-01-13 22:49:00
 * 大对象直接分配到老年代
 * -Xms60m -Xmx60m -XX:NewRatio=2 -XX:SurvivorRatio=8 -XX:+PrintGCDetails
 * 60m的堆内存按照新生/老年：1/2 Eden/s：8/1的比例来分的话，Eden有16 S有2 老年代有40
 */
public class YoungOldTest {
    public static void main(String[] args) {
        //20m，比Eden大的直接放入老年代
        byte[] buffer = new byte[1024*1024*20];
    }
}
