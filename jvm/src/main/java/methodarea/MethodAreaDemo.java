package methodarea;

/**
 * @author :覃玉锦
 * @create :2021-01-14 11:19:00
 * 设置方法区的大小的参数：
 * jdk7之前:
 * -XX:PermSize=size -XX:MaxPermSize=size
 * jdk8之后：
 * -XX:MetaspaceSize=size  -XX:MaxMetaspaceSize=size
 *
 * 建议开发中初始方法区设置稍微大一些，最大方法区默认值
 */
public class MethodAreaDemo {
    public static void main(String[] args) throws InterruptedException {
        //初始方法区大小20m
        System.out.println("start");
        Thread.sleep(100000000);
    }
}
