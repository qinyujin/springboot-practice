package juc.code.concurrent_feature_jmm;

/**
 * @author :覃玉锦
 * @create :2023-08-02 23:05:00
 * 原子性问题.volatile虽然保证了可见性和有序性，但是在count++这种复合操作中还是不具有原子性。虽然操作都是在主内存中进行的。
 * 可以使用Atomic包下的类，比如AtomicInteger来解决。它的实现方式是通过volatile+CAS
 */
public class AtomicTest {
    private volatile int count = 0;

    public void add() {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> count++).start();
        }
    }

    public void subtract() {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> count--).start();
        }
    }

    public static void main(String[] args) {
        AtomicTest test = new AtomicTest();
        test.add();
        test.subtract();
        System.out.println(test.count);
    }
}
