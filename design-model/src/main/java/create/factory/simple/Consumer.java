package create.factory.simple;

/**
 * @author :覃玉锦
 * @create :2021-01-18 11:26:00
 */
public class Consumer {
    public static void main(String[] args) {
        //原始方式，调用者自己来创建对象
        /*Car car = new WuLing();
        Car car2 = new Tesla();*/

        //简单工厂方式，通过工厂来创建车
        Car car = CarFactory.getCar("五菱");
        Car car2 = CarFactory.getCar("特斯拉");

        car.name();
        car2.name();
    }
}

interface Car{
    void name();
}

class CarFactory{
    //简单工厂方式，缺点是每增加一个车种类都需要修改代码
    public static Car getCar(String car){
        if(car.equals("五菱")){
            return new WuLing();
        }
        else if(car.equals("特斯拉")){
            return new Tesla();
        }
        else {
            return null;
        }
    }
}

class Tesla implements Car{
    @Override
    public void name() {
        System.out.println("特斯拉");
    }
}

class WuLing implements Car{
    @Override
    public void name() {
        System.out.println("五菱宏光");
    }
}
