import java.util.concurrent.locks.ReentrantLock;

/**
 * @author :覃玉锦
 * @create :2021-01-26 21:29:00
 */
public class Test {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
    }
}
