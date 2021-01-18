package runtime;

/**
 * @author :覃玉锦
 * @create :2021-01-13 23:43:00
 * 开启逃逸分析，让部分的对象建立在栈中
 * -Xms256m -Xmx256m -XX:PrintGCDetails -XX:+/-DoEscapeAnalysis
 */
public class StackAllocation {
    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            alloc();
        }
        long end = System.currentTimeMillis();
        //不开启逃逸分析477 开启逃逸分析5
        System.out.println("耗费时间："+(end - begin));
    }

    public static void alloc(){
        //因为没有逃逸，因此可以优化到栈上
        User user = new User();
    }
}
