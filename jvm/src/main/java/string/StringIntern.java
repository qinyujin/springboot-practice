package string;

/**
 * @author :覃玉锦
 * @create :2021-01-16 00:25:00
 * 给字符串常量池复制的方式
 * 方式一：String str = "hello";
 * 方式二：String str = new String("hello").intern()
 * intern方法会去字符串常量池里找，如果有字符返回对象地址，没有则把当前地址放入常量池。
 */
public class StringIntern {
    public static void main(String[] args) {
        String s1 = new String("1");
        s1.intern();
        String s2 = "1";
        //调用intern但是常量池已经有了值，所以返回常量池中的地址，但是没有接收。
        //jdk6-false jdk7/8-true
        System.out.println(s1 == s2);

        String s3 = new String("1")+new String("1");
        s3.intern();
        String s4 = "11";
        //因为"11"一开始是拼接的，没有在常量池中，那么调用intern之后s3的地址就放入常量池。
        // jdk6因为常量池不是在堆中，因此地址绝对不可能一样
        //jdk-false jdk7/8-true
        System.out.println(s3 == s4);
    }
}
