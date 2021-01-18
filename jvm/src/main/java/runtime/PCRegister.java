package runtime;

/**
 * @author :覃玉锦
 * @create :2021-01-09 10:43:00
 * PC寄存器 Program Count Register
 * PC寄存器主要记录指令地址，因为cpu的并发是时间片轮转，会在多个线程之间切换，因此每个线程的PC寄存器记录指令地址，方便
 * cpu切换的时候继续接着上一次的操作
 */
public class PCRegister {
    public static void main(String[] args) {
        int i = 10;
        int j = 20;
        int k = i + j;

        String s = "abc";
        System.out.println(i);
        System.out.println(k);
    }
}
