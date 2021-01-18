package methodarea;

/**
 * @author :覃玉锦
 * @create :2021-01-15 19:53:00
 * static都在方法区中共享，静态类不需要有实例，其他类也可以访问
 * 加了final的变量在编译期就确定值了
 */
public class MethodAreaTest {
    public static void main(String[] args) {
        Order order = null;
        order.hello();
        System.out.println(order.count);
    }
}

class Order{
    public static int count = 10;
    public static final int number = 1314;

    public static void hello(){
        System.out.println("hello");
    }
}
