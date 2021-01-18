package classloader;

/**
 * @author :覃玉锦
 * @create :2021-01-08 09:01:00
 * 测试jvm字节码指令，指令集架构一般有两种，栈或寄存器，jvm的hotSpot使用的是栈
 */
public class StackStructureTest {
    public static void main(String[] args) {
//        int i = 2 + 3;
        int i = 2;
        int j = 3;
        int k = i + j;

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
