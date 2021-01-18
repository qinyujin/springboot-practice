package practice;

import lombok.Data;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

/**
 * @author :覃玉锦
 * @create :2021-01-18 15:36:00
 */
public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        //创建型模式又可以分成单例、简答工厂、工厂方法、抽象工厂、建造者、原型模式
        //1.1、单例饿汉模式
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    Hungry.getInstance();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        //1.2、单例懒汉模式
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    Lazy.getInstance();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        //2.1、简单工厂模式
        //原始情况
        System.out.println("=========old scene=========");
        Car tesla = new Tesla();
        Car lamborghini = new Lamborghini();
        tesla.name();
        lamborghini.name();
        //简单工厂来创建
        System.out.println("=========simple create.factory=========");
        CarFactory factory = new CarFactory();
        factory.getCar("兰博基尼").name();
        CarFactory.getTesla().name();
        //工厂方法来创建
        System.out.println("=========create.factory method=========");
        new TeslaFactory().getCar().name();
        new LamborghiniFactory().getCar().name();
        //抽象工厂模式来创建
        System.out.println("=========abstract create.factory method=========");
        XiaomiFactory xiaomiFactory = new XiaomiFactory();
        xiaomiFactory.productPhone().start();
        xiaomiFactory.productRouter().openWifi();

        //3、建造者模式，需要调用指挥者。也可以让用户当指挥者，直接调用builder
        System.out.println("=========create.builder=========");
        Director director = new Director();
        Product build = director.build(new Worker());
        System.out.println(build);

        //4、原型模式
        System.out.println("=========create.prototype=========");
        Video v1 = new Video();
        Date date = new Date();
        v1.setName("我爱中国");
        v1.setCreateTime(date);
        Video v2 = (Video) v1.clone();
        System.out.println("更改日期之前");
        System.out.println(v1);
        System.out.println(v2);
        System.out.println("更改日期之后");
        date.setTime(1242142);
        System.out.println(v1);
        System.out.println(v2);

        //5、适配器模式
        Adapter adapter = new Adapter();
        int vol = adapter.charging5();
        System.out.println(vol);

        //6、桥接模式
        Computer desktop = new Desktop(new Apple());
        Computer laptop = new Laptop(new Huipu());
        desktop.info();
        laptop.info();

        //7.1代理模式-静态代理
        Human man = new Man();
        Human woman = new Woman();
        StaticProxy staticProxy = new StaticProxy(man);
        StaticProxy staticProxy1 = new StaticProxy(woman);
        staticProxy.proxy();
        staticProxy1.proxy();

        //7.2jdk动态代理。使用反射Proxy.newInstance和invocationHandle来实现
        JdkProxy jdkProxy = new JdkProxy();
        Human manProxy = (Human) jdkProxy.getProxyObject(man);
        manProxy.eat();

        //7.3 cglib动态代理，直接代理类
        Person person = new Person();
        CglibProxy cglibProxy = new CglibProxy();
        Person p = (Person) cglibProxy.bind(person);
        p.dream();
    }
}

class Hungry {
    private static final Hungry instance = new Hungry();

    private Hungry() {
        System.out.println(Thread.currentThread().getName() + "饿汉单例模式");
    }

    public static Hungry getInstance() throws InterruptedException {
        Thread.sleep(200);
        return instance;
    }
}

class Lazy {
    private volatile static Lazy instance;

    private Lazy() {
        System.out.println(Thread.currentThread().getName() + "单例懒汉模式");
    }

    public static Lazy getInstance() throws InterruptedException {
        if (instance == null) {
            Thread.sleep(200);
            synchronized (Lazy.class) {
                if (instance == null) {
                    instance = new Lazy();
                }
            }
        }

        /*if (instance == null) {
            Thread.sleep(200);
            instance = new Lazy();
        }*/
        return instance;
    }
}

/*=================================简单工厂=================================*/
interface Car {
    void name();
}

class CarFactory {
    //写死方法
    public Car getCar(String carName) {
        if (carName.equals("特斯拉")) {
            return new Tesla();
        } else if (carName.equals("兰博基尼")) {
            return new Lamborghini();
        } else {
            return null;
        }
    }

    public static Car getLamborghini() {
        return new Lamborghini();
    }

    public static Car getTesla() {
        return new Tesla();
    }
}

interface CarFactory1 {
    Car getCar();
}

class TeslaFactory implements CarFactory1 {
    @Override
    public Car getCar() {
        return new Tesla();
    }
}

class LamborghiniFactory implements CarFactory1 {
    @Override
    public Car getCar() {
        return new Lamborghini();
    }
}

class Tesla implements Car {
    @Override
    public void name() {
        System.out.println("特斯拉");
    }
}

class Lamborghini implements Car {
    @Override
    public void name() {
        System.out.println("兰博基尼");
    }
}

interface PhoneProduct {
    void start();

    void shutdown();

    void call();

    void sendMsg();
}

interface RouterProduct {
    void start();

    void shutdown();

    void openWifi();

    void setting();
}

interface ProductFactory {
    PhoneProduct productPhone();

    RouterProduct productRouter();
}

class XiaomiPhone implements PhoneProduct {
    @Override
    public void start() {
        System.out.println("小米手机开机");
    }

    @Override
    public void shutdown() {
        System.out.println("小米手机关机");
    }

    @Override
    public void call() {
        System.out.println("小米手机打电话");
    }

    @Override
    public void sendMsg() {
        System.out.println("小米手机发短信");
    }
}

class XiaomiRouter implements RouterProduct {
    @Override
    public void start() {
        System.out.println("小米路由器开机");
    }

    @Override
    public void shutdown() {
        System.out.println("小米路由器关机");

    }

    @Override
    public void openWifi() {
        System.out.println("小米路由器开启wifi");

    }

    @Override
    public void setting() {
        System.out.println("小米路由器设置参数");

    }
}

class XiaomiFactory implements ProductFactory {
    @Override
    public PhoneProduct productPhone() {
        return new XiaomiPhone();
    }

    @Override
    public RouterProduct productRouter() {
        return new XiaomiRouter();
    }
}

/*=================================建造者模式=================================*/
//建造的产品。
//具体建造者来建造产品
//需要一个指挥者来调用构建者进行构造
//
//建造者，提供通用的建造方法
abstract class Builder {
    abstract void buildA();

    abstract void buildB();

    abstract void buildC();

    abstract void buildD();

    abstract Product getProduct();
}

@Data
class Product {

    private String buildA;

    private String buildB;

    private String buildC;

    private String buildD;
}

class Worker extends Builder {

    private Product product;

    public Worker() {
        this.product = new Product();
    }

    @Override
    void buildA() {
        this.product.setBuildA("地基");
        System.out.println("地基");
    }

    @Override
    void buildB() {
        this.product.setBuildB("钢筋");
        System.out.println("钢筋");
    }

    @Override
    void buildC() {
        this.product.setBuildC("电线");
        System.out.println("电线");
    }

    @Override
    void buildD() {
        this.product.setBuildD("粉刷");
        System.out.println("粉刷");
    }

    @Override
    Product getProduct() {
        return this.product;
    }
}

class Director {

    public Product build(Builder builder) {
        builder.buildA();
        builder.buildB();
        builder.buildC();
        builder.buildD();

        return builder.getProduct();
    }
}

@Data
class Video implements Cloneable {

    private String name;

    private Date createTime;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        //拿到父克隆对象
        Video obj = (Video) super.clone();
        //把属性也克隆
        obj.createTime = (Date) this.createTime.clone();
        return obj;
    }
}

/*=================================适配器模式=================================*/
class Voltage220 {
    public int charging220() {
        int voltage = 220;
        System.out.println("220V电压");
        return voltage;
    }
}

interface Voltage5 {
    int charging5();
}

class Adapter extends Voltage220 implements Voltage5 {
    //在这里可以拿到两个类的方法

    @Override
    public int charging5() {
        int oldVol = super.charging220();
        int retVal = oldVol / 44;
        System.out.println("适配开始，把" + oldVol + "适配为" + retVal);
        return retVal;
    }
}

interface Brand {
    void info();
}

class Huipu implements Brand {
    @Override
    public void info() {
        System.out.print("惠普");
    }
}

class Apple implements Brand {
    @Override
    public void info() {
        System.out.print("苹果");
    }
}

abstract class Computer {
    protected Brand brand;

    public Computer(Brand brand) {
        this.brand = brand;
    }

    public void info() {
        brand.info();
    }
}

class Desktop extends Computer {
    public Desktop(Brand brand) {
        super(brand);
    }

    @Override
    public void info() {
        super.info();
        System.out.println("台式机");
    }
}

class Laptop extends Computer {
    public Laptop(Brand brand) {
        super(brand);
    }

    @Override
    public void info() {
        super.info();
        System.out.println("笔记本");
    }
}

interface Human {
    void eat();
}

class Man implements Human {
    @Override
    public void eat() {
        System.out.println("人吃饭");
    }
}

class Woman implements Human {
    @Override
    public void eat() {
        System.out.println("女人吃饭");
    }
}

class StaticProxy {
    private Human human;

    public StaticProxy(Human human) {
        this.human = human;
    }

    public void proxy() {
        System.out.println("人类清除计划");
        human.eat();
    }
}

class JdkProxy {
    public Object getProxyObject(Object object) {
        MyInvocationHandle handle = new MyInvocationHandle(object);
        Object retValue = Proxy.newProxyInstance(object.getClass().getClassLoader(),
                object.getClass().getInterfaces(),
                handle);
        return retValue;
    }
}

class MyInvocationHandle implements InvocationHandler {
    private Object object;

    public MyInvocationHandle(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdk proxy");
        Object retValue = method.invoke(object, args);
        return retValue;
    }
}

class Person {
    public void dream() {
        System.out.println("人往高处走，水往低处流");
    }
}

class CglibProxy implements MethodInterceptor {
    private Object object;

    public Object bind(Object obj) {
        object = obj;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.object.getClass());
        enhancer.setCallback(this);
        Object retValue = enhancer.create();
        return retValue;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib proxy");
        Object retValue = method.invoke(object, objects);
        return retValue;
    }
}
