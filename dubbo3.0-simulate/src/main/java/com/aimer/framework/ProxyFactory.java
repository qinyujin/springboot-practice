package com.aimer.framework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory<T> {

    @SuppressWarnings("unchecked")
    public static <T> T getProxy(final Class interfaceClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //可以模拟输出结果,对本次方法调用的增强
                String mock = System.getProperty("mock");
                if (mock != null && mock.startsWith("return:")) {
                    String result = mock.replace("return:", "");
                    return result;
                }

                Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(), args, method.getParameterTypes());

                //根据不同协议获取到具体的invoker,例如dubboInvoker、httpInvoker、triInvoker
                Invoker invoker = ClusterInvoker.join(interfaceClass);

                //调用具体方法就是通过代理对象调invoke并且传invocation参数过去
                return invoker.invoke(invocation);

            }
        });
    }

}
