package runtime;

/**
 * @author :覃玉锦
 * @create :2021-01-13 16:46:00
 * 虚拟机栈额常见异常：
 * 1、StackOverflowError栈溢出
 * 2、OutOfMemoryError
 */
public class OutOfMemoryTest {
    public static void main(String[] args) {
        OutOfMemoryTest oom = new OutOfMemoryTest();
        oom.methodA();
    }

    public void methodA() {
        methodA();
    }
}
