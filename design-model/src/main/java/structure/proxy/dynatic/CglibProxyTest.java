package structure.proxy.dynatic;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author :覃玉锦
 * @create :2021-01-18 19:24:00
 * cglib可以直接代理类，效率高
 */
public class CglibProxyTest {
    public static void main(String[] args) {
        Person p = new Person();
        CglibProxyFactory interceptor = new CglibProxyFactory();
        Person proxy = (Person) interceptor.bind(p);
        proxy.dream();
    }
}

class Person{
    public void dream(){
        System.out.println("人往高处走，水往低处流");
    }
}

class CglibProxyFactory implements MethodInterceptor{
    private Object object;

    public Object bind(Object obj){
        this.object = obj;
        Enhancer enhancer = new Enhancer();
        //设置父类为传入的对象
        enhancer.setSuperclass(this.object.getClass());
        //设置回调
        enhancer.setCallback(this);
        //创建代理对象
        return enhancer.create();
    }

    //拦截方法，拦截原来的方法。method就是原来的方法。
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib proxy");
        Object retValue = method.invoke(this.object, objects);
        return retValue;
    }
}
