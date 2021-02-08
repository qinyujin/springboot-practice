package motivation.stratege;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author :覃玉锦
 * @create :2021-01-19 21:44:00
 * 策略模式。在策略模式（Strategy Pattern）中，一个类的行为或其算法可以在运行时更改。
 * 这里Context的执行就可以通过不通过的策略来实现不同的效果
 */
@SpringBootApplication
public class StrategyTest {
    public static void main(String[] args) {
        SpringApplication.run(StrategyTest.class,args);
        AddStrategy add = new AddStrategy();
        Context context = new Context(add);
        System.out.println(context.execute(2, 7));

        context = new Context(new SubtractStrategy());
        System.out.println(context.execute(5, 3));

        context = new Context(new MultiStrategy());
        System.out.println(context.execute(5, 6));
    }
}

//策略模式，策略接口，这里提供3个：加减乘
interface Strategy{
    int culculate(int num1,int num2);
}

class AddStrategy implements Strategy{
    @Override
    public int culculate(int num1, int num2) {
        return num1+num2;
    }
}

class SubtractStrategy implements Strategy{
    @Override
    public int culculate(int num1, int num2) {
        return num1-num2;
    }
}

class MultiStrategy implements Strategy{
    @Override
    public int culculate(int num1, int num2) {
        return num1*num2;
    }
}

//有了三个策略类，现在使用一个context类来执行各种策略
class Context{
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public int execute(int num1,int num2){
        return strategy.culculate(num1,num2);
    }
}
