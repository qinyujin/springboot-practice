package runtime;

/**
 * @author :覃玉锦
 * @create :2021-01-09 11:18:00
 * 虚拟机栈是每个线程有一个，没有gc，但是又oom，栈又有栈帧，每个方法对应一个栈帧
 */
public class StackTest {
    public static void main(String[] args) {
        StackTest stackTest = new StackTest();
        stackTest.method1();
    }

    public void method1(){
        int i =10;
        int j =20;
        method2();
    }

    public void method2(){
        int k = 30;
        int m = 40;
    }
}
