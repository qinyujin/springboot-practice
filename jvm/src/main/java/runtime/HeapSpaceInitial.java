package runtime;

/**
 * @author :覃玉锦
 * @create :2021-01-13 20:19:00
 * 堆空间的设置参数：-Xms10m 设置初始堆内存大小（memory start）
 * -Xmx10m 设置堆最大内存
 *
 * 默认堆内存：
 * 初始：物理内存/64
 * 最大：物理内存/4
 *
 * 查看设置的参数：
 * 方式一：jps/jstat -gc 进程id
 * 方式二：-XX:+PrintGCDetails
 */
public class HeapSpaceInitial {
    public static void main(String[] args) {
        long initialMemory = Runtime.getRuntime().totalMemory()/1024/1024;
        long maxMemory = Runtime.getRuntime().maxMemory()/1024/1024;

        System.out.println("-Xms:"+initialMemory+"M");
        System.out.println("-Xmx:"+maxMemory+"M");

        System.out.println("系统内存为"+initialMemory*64.0/1024+"G");
        System.out.println("系统内存为"+initialMemory*4.0/1024+"G");
    }
}
