package motivation.observe;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-01-19 17:29:00
 * 观察者模式
 * 1、有被观察的对象
 * 2、有观察者（多）
 *
 * 需要做的就是当被观察对象有改变之后，所有观察者能观察到变化的结果。
 *
 * 实现思路：
 * 1、被观察者中应有观察者集合类变量用来存储观察者。
 * 2、观察者需要有自己的update方法来更新观察对象信息
 */
public class ObserveTest {
    public static void main(String[] args) {
        //被观察者
        Subject subject = new Subject();
        //三个观察者
        BinaryObserver binaryObserver = new BinaryObserver(subject);
        OctalObserver octalObserver = new OctalObserver(subject);
        HexObserve hexObserve = new HexObserve(subject);

        //修改subject
        System.out.println("第一次修改被观察者");
        subject.setNum(10);
        System.out.println("第二次修改被观察者");
        subject.setNum(20);
    }
}

//被观察者
class Subject{
    private List<Observer> observers = new ArrayList<>();
    private int num;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
        //产生变动时就通知其他观察者
        notifyAllObservers();
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }
}

//抽象观察者类。因为需要属性，所以使用抽象类不使用接口
abstract class Observer{
    protected Subject subject;
    public abstract void update();
}

class BinaryObserver extends Observer{
    public BinaryObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("二进制：" + Integer.toBinaryString(super.subject.getNum()));
    }
}

class OctalObserver extends Observer{

    public OctalObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("十进制："+super.subject.getNum());
    }
}

class HexObserve extends Observer{
    public HexObserve(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("十六进制："+Integer.toHexString(super.subject.getNum()));
    }
}
