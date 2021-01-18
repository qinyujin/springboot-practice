package classloader;

/**
 * @author :覃玉锦
 * @create :2021-01-08 23:39:00
 * 获取ClassLoader的几种方式
 */
public class ClassLoaderTest2 {
    public static void main(String[] args) throws ClassNotFoundException {
        //1
        ClassLoader classLoader = Class.forName("java.lang.String").getClassLoader();
        System.out.println(classLoader);

        //2
        ClassLoader classLoader1 = Thread.currentThread().getContextClassLoader();
        System.out.println(classLoader1);

        //3
        ClassLoader classLoader2 = ClassLoader.getSystemClassLoader();
        System.out.println(classLoader2);
    }
}
