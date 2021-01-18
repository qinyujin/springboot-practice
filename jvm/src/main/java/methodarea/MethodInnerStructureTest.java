package methodarea;

import java.io.Serializable;

/**
 * @author :覃玉锦
 * @create :2021-01-15 19:03:00
 * 方法区的内部结构。
 */
public class MethodInnerStructureTest extends Object implements Comparable, Serializable {
    //属性，普通和静态
    public int num = 1;
    private static String str = "测试方法区内部结构";
    //构造器
    //方法，普通和静态
    public void test1(){
        int count = 10;
        System.out.println("count = "+count);
    }

    public static int test2(int val){
        int result = 0;
        try {
            int value = 30;
            result = value/val;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
