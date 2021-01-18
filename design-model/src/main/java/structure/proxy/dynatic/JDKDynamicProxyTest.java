package structure.proxy.dynatic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author :覃玉锦
 * @create :2021-01-18 19:02:00
 * jdk代理只针对接口，使用Proxy.newProxyInstance 的反射的方式来获取新的代理对象。因为这个方法的第二个参数是接口集，所以只能接口使用
 */
public class JDKDynamicProxyTest {
    public static void main(String[] args) {
        ClothFactory nike = new Nike();
        Human man = new Man();
        //一个代理类可以代理多个接口，对于任何接口都可以进行代理操作
        ProxyFactory proxyFactory = new ProxyFactory();
        //接口1
        ClothFactory proxy = (ClothFactory) proxyFactory.getProxyObject(nike);
        //接口2
        Human proxy2 = (Human) proxyFactory.getProxyObject(man);

        proxy.procudure();
        proxy2.sex();
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
