package structure.proxy.dynatic;

import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author :覃玉锦
 * @create :2021-01-18 19:02:00
 * jdk代理只针对接口，使用Proxy.newProxyInstance 的反射的方式来获取新的代理对象。因为这个方法的第二个参数是接口集，所以只能接口使用
 */
public class JDKDynamicProxyTest {
    public static void main(String[] args) throws IOException {
        ClothFactory nike = new Nike();
        Human man = new Man();
        //一个代理类可以代理多个接口，对于任何接口都可以进行代理操作
        ProxyFactory proxyFactory = new ProxyFactory();
        //接口1
        ClothFactory proxy = (ClothFactory) proxyFactory.getProxyObject(nike);
        //接口2
        Human proxy2 = (Human) proxyFactory.getProxyObject(man);
        System.out.println(proxy.getClass().getName()); //$Proxy0
        System.out.println(proxy2.getClass().getName()); //$Proxy1

        proxy.procudure();
        proxy2.sex();


        //把代理对象类写入到硬盘来分析动态代理过程
        String proxyName = "$Proxy0";
        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("代理前");
                Object invoke = method.invoke(nike, args);
                System.out.println("代理后");
                return invoke;
            }
        };
        byte[] bytes = ProxyGenerator.generateProxyClass(
                proxyName, nike.getClass().getInterfaces(), 1);
        File file = new File("D:\\Idea\\ideaWorkplace\\springboot-practice\\design-model\\src\\main\\java\\structure\\proxy\\dynatic\\$Proxy.class");
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bytes);
    }
}
/*===========================接口1===========================*/
interface ClothFactory{
    void procudure();
}

class Nike implements ClothFactory{
    @Override
    public void procudure() {
        System.out.println("耐克打钱");
    }
}

/*===========================接口2===========================*/
interface Human{
    void sex();
}

class Man implements Human{
    @Override
    public void sex() {
        System.out.println("男性");
    }
}

class ProxyFactory{
    public Object getProxyObject(Object oldObj){
        MyInvocationHandler handler = new MyInvocationHandler(oldObj);
        Object newObj = Proxy.newProxyInstance(oldObj.getClass().getClassLoader(),
                oldObj.getClass().getInterfaces(),
                handler);
        return newObj;
    }
}

class MyInvocationHandler implements InvocationHandler{
    private Object object;

    public MyInvocationHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("动态代理前");
        Object retValue = method.invoke(object, args);
        System.out.println("动态代理后");
        return retValue;
    }
}
