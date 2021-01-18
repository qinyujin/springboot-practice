package runtime;

/**
 * @author :覃玉锦
 * @create :2021-01-13 14:46:00
 * 操作数栈，主要存储计算过程中产生的中间结果和临时变量，如果调用的方法有返回值也会放入当前栈帧的操作数栈中。
 */
public class OperandStackTest {
    public void operandStackTest(){
        int i = 15;
        int j = 8;
        int k = i+j;
    }
}

