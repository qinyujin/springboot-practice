package structure.decoration;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author :覃玉锦
 * @create :2021-01-19 11:34:00
 * 装饰者模式是对于一个接口的实现类来说，可以写一个抽象的类（装饰类）也实现接口，同时把接口类作为类变量。这样装饰类就可以同时
 * 实现具体类的方法并且加以修改。
 *
 * 问题示例:现在有年轻男人/老年男人。需要对他们的情况进行确定。通过装饰模式可以把有房/有车。。。等情况装饰到具体的人身上
 */
public class DecorateTest {
    public static void main(String[] args) throws FileNotFoundException {
        Man youngMan = new YoungMan();
        Man oldMan = new OldMan();
        //未经任何装饰的原始类
        youngMan.manDesc();
        System.out.println();
        oldMan.manDesc();
        System.out.println();

        //进行装饰
        CarDesc youngMan1 = new CarDesc(youngMan);
        youngMan1.manDesc();
        System.out.println();

        Man oldMan1 = new CarDesc(oldMan);
        Man oldMan2 = new HouseDesc(oldMan1);
        Man oldMan3 = new MoneyDesc(oldMan2);
        Man oldMan4 = new CarDesc(oldMan3);
        //经过四次装饰的oldMan
        oldMan4.manDesc();

        File file = new File("1.jpg");
        //jdk 的io也是装饰者模式
        //1、inputStream是抽象类，FileInputStream是具体类
        //2、FilterInputStream是装饰类父类。
        //3、BufferedInputStream是FilterInputStream的子类，同时也是具体装饰类
        //4、因此可以使用BufferedInputStream来对FileInputStream进行装饰。
        BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(file));
    }
}

interface Man{
    //一个人的具体情况，可以通过修饰类给添加 有车/有房/有票子
    void manDesc();
}

class YoungMan implements Man{
    @Override
    public void manDesc() {
        System.out.print("年轻人:");
    }
}

class OldMan implements Man{
    @Override
    public void manDesc() {
        System.out.print("老头子:");
    }
}

//装饰抽象类需要实现接口并且也要把接口作为类变量
abstract class Description implements Man{
    private Man man;

    public Description(Man man) {
        this.man = man;
    }

    //抽象类子类可以在这个方法对具体类进行装饰
    @Override
    public void manDesc() {
        man.manDesc();
    }
}

//具体装饰类来添加装饰的类别，对具体类进行装饰
class CarDesc extends Description{
    public CarDesc(Man man) {
        super(man);
    }

    @Override
    public void manDesc() {
        //原来的具体类的方法，在这里就可以进行装饰
        super.manDesc();
        System.out.print("有车 ");
    }
}

class HouseDesc extends Description{
    public HouseDesc(Man man) {
        super(man);
    }

    @Override
    public void manDesc() {
        super.manDesc();
        System.out.print("有房 ");
    }
}

class MoneyDesc extends Description{
    public MoneyDesc(Man man) {
        super(man);
    }

    @Override
    public void manDesc() {
        super.manDesc();
        System.out.print("有钱 ");
    }
}
