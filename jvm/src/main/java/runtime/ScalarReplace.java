package runtime;

/**
 * @author :覃玉锦
 * @create :2021-01-14 10:07:00
 * 逃逸分析-标量替换
 * 标量在java里就是说可以把聚合类型的数据分解为基本数据类型
 * -Xmx100m -Xms100m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:-EliminateAllocations(是否开启标量替换)
 */
public class ScalarReplace {
    public static class User{
        public int id;
        public String name;
    }

    public static void alloc(){
        User user = new User();//未发生逃逸
        user.id = 5;
        user.name="qyj";
    }

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            alloc();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗费时间："+(end - begin));
    }
}
