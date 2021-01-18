package string;

import org.junit.Test;

/**
 * @author :覃玉锦
 * @create :2021-01-16 00:00:00
 * 字符串与字符串常量池的联系：
 * 如果字符串调用intern方法，那么会去常量池中找，如果没有则把当前对象地址放入，有则返回对象地址。
 * 字符串拼接底层原理是new一个StringBuilder对象，调用Append方法拼接，最后调用StRingBuilder的toString（
 * 底层也是new String（））
 */
public class StringTest {
    @Test
    public void test1() {
        String s1 = "a" + "b" + "c";
        String s2 = "abc";
        //true
        System.out.println(s1 == s2);
        //true
        System.out.println(s1.equals(s2));
    }

    @Test
    public void test2(){
        String s1 = "javaEE";
        String s2 = "hadoop";

        String s3 = "javaEEhadoop";
        String s4 = "javaEE"+"hadoop";
        //有变量参与，相当于new String。new String（）一般创建两个对象，一个是常量池的对象，一个是堆里的
        String s5 = s1 + "hadoop";
        String s6 = "javaEE" + s2;
        String s7 = s1 + s2;

        System.out.println(s3 == s4);//true
        System.out.println(s3 == s5);//false
        System.out.println(s3 == s6);//false
        System.out.println(s3 == s7);//false
        System.out.println(s5 == s6);//false
        System.out.println(s5 == s7);//false
        System.out.println(s6 == s7);//false

        //调用intern则去常量池找。这里有所以返回地址
        String s8 = s6.intern();
        System.out.println(s3 == s8);//true
    }

    @Test
    public void test3(){
        String s1 = "a";
        String s2 = "b";
        String s3 = "ab";
        //字符串拼接查看字节码可知是new StringBuilder，然后调用append方法追加了a和b，然后调用toString方法
        String s4 = s1+s2;
        System.out.println(s3 == s4);//false
    }

    @Test
    public void test4(){
        //final修饰就不是变量了，所以s4是常量拼接，编译期优化s4就是常量，可以查看字节码
        final String s1 = "a";
        final String s2 = "b";
        String s3 = "ab";
        String s4 = s1+s2;
        System.out.println(s3 == s4);//true
    }

    //Stirng和StringBuilder追加效率测试
    @Test
    public void test5(){
        //字符串直接拼接的话会创建StringBuilder对象、String对象（toString阶段），因此会涉及到垃圾回收，效率很低
        //8786
//        method1();
        //8
        method2();
    }

    public void method1(){
        long begin = System.currentTimeMillis();
        String str = "a";
        for (int i = 0; i < 100000; i++) {
            str = str+"a";
        }
        long end = System.currentTimeMillis();
        System.out.println("耗费时间："+(end - begin));
    }

    public void method2(){
        long begin = System.currentTimeMillis();
        StringBuilder str = new StringBuilder("a");
        for (int i = 0; i < 100000; i++) {
            str.append("a");
        }
        long end = System.currentTimeMillis();
        System.out.println("耗费时间："+(end - begin));
    }
}
