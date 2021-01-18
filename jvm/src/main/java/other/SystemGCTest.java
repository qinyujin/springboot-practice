package other;

import java.lang.ref.WeakReference;

/**
 * @author :覃玉锦
 * @create :2021-01-16 02:17:00
 * System.gc或Runtime.getRuntime.gc可以显示调用full gc，
 */
public class SystemGCTest {
    public static void main(String[] args) {
        new SystemGCTest();
        System.gc();
//        Runtime.getRuntime().gc();

        Object obj = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(obj);
        obj = null;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("垃圾被回收了");
    }
}
