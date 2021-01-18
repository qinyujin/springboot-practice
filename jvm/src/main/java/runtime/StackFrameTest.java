package runtime;

/**
 * @author :覃玉锦
 * @create :2021-01-09 11:28:00
 * 栈帧是虚拟机栈中的一个组成部分，可以把栈帧看作虚拟机栈的元素，每次有一个方法就压入一个栈帧。只能有一个栈帧在栈内运行    
 */
public class StackFrameTest {
    public static void main(String[] args) {
        StackFrameTest stackFrameTest = new StackFrameTest();
        stackFrameTest.method1();
        System.out.println("main()正常结束");
    }

    public void method1(){
        System.out.println("method1()开始运行");
        method2();
        System.out.println("method1()结束运行");
        //该栈帧产生的异常会抛给main线程
//        System.out.println(1 / 0);
    }

    public int method2(){
        System.out.println("method2()开始运行");
        int i =10;
        double m = method3();
        System.out.println("method2()即将结束");
        return i+(int)m;
    }

    public double method3(){
        System.out.println("method3()开始运行");
        double m = 20.0;
        System.out.println("method3()结束运行");
        return m;
    }
}


