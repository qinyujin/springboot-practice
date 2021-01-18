package executeengine;

/**
 * @author :覃玉锦
 * @create :2021-01-14 21:50:00
 * String的intern方法可以把String的值与字符串常量池进行比较，如果池里有，返回地址，没有则加入到里面。
 */
public class StringInternTest {
    public static void main(String[] args) {
        String s = new String("1");
        //如果接收的话地址就一样，不接受的话就不一样
//        s = s.intern();
        s.intern();
        String s2 = "1";
        //jdk6 false   jdk7/8 false
        System.out.println(s == s2);

        String s3 = new String("1")+new String("1");
        //不接收也一样，因为当前常量池没有11，s3的地址就被指向常量池中。
        s3.intern();
        String s4 = "11";
        //jdk6 false  jdk7/8 true
        System.out.println(s3 == s4);
    }
}
