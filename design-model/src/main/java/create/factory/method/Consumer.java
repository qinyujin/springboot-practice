package create.factory.method;


/**
 * @author :覃玉锦
 * @create :2021-01-18 11:26:00
 */
public class Consumer {
    public static void main(String[] args) {
        //通过了车工厂来创建车，好处是不用改代码，坏处是需要更多的类，一类车就需要一个工厂类和车类
        Car car = new WuLingFactory().getCar();
        Car car2 = new TeslaFactory().getCar();

        car.name();
        car2.name();
    }
}
