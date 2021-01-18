package runtime;

/**
 * @author :覃玉锦
 * @create :2021-01-09 01:36:00
 * jvm虚拟机栈有可能出现的异常：
 * 1、OOM outOfMemory 内存溢出
 * 2、StackOverFlow 栈内存溢出
 */
public class StackErrorTest {
    //9772   256k-2197
    private static int num = 1;
    public static void main(String[] args) {
        System.out.println(num++);
        //StackOverflowError
        main(args);
    }
}
