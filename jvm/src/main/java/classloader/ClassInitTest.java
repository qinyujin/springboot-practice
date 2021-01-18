package classloader;

/**
 * @author :覃玉锦
 * @create :2021-01-08 19:32:00
 */
public class ClassInitTest {

    static {
        num = 20;
    }
    private static int num = 10;

    public static void main(String[] args) {
        System.out.println(num);//10
    }
}
