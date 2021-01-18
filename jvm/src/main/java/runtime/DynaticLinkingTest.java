package runtime;

/**
 * @author :覃玉锦
 * @create :2021-01-11 20:52:00
 * 动态链接，链接又有静态链接和动态链接，静态链接是编译时就可以确定好的，动态链接则是运行时才能确定的（继承类，接口）
 */
public class DynaticLinkingTest {

    int num = 10;

    public void methodA(){
        System.out.println("methodA()...");
    }

    public void methodB(){
        System.out.println("methodB()...");

        methodA();

        num++;
    }
}
