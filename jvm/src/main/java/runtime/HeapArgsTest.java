package runtime;

/**
 * @author :覃玉锦
 * @create :2021-01-13 22:58:00
 * jvm设置的常用参数
 * -XX:+PrintFlagsInitial     查看所有参数的初始默认值
 * -XX:+PrintFlagsFinal       查看所有参数的最终值
 *      具体查看m某个参数的指令：jps: 查看当前运行中的进程
 *                           jinfo -flag SurvivorRatio 进程号
 *
 * -Xms                       初始堆空间的内存（默认1/64）
 * -Xmx                       最大堆空间的内存（默认1/4）
 * -Xmn                       设置新生代的大小初始值及最大值
 * -XX:NewRatio               配置新生代与老年代的比值
 * -XX:InitialSurvivorRatio   初始时的Eden和s区的比，例如值是8，则比值是6:1:1
 * -XX:SurvivorRatio          Eden区与s区的比值，例如值是8，则比值是8:1:1
 * -XX:MaxTenuringThreshold   最大年龄数
 * -XX:+PrintGCDetails        打印GC详细信息
 *     打印简要信息：① -XX:+PrintGC ② -verbose:gc
 * -XX:HandlePromotionFailure 是否设置空间分配担保
 */
public class HeapArgsTest {
    public static void main(String[] args) {
        
    }
}
