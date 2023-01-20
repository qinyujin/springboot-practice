package com.aimer.framework;

import com.aimer.framework.protocol.http.HttpClient;
import com.aimer.framework.register.RemoteRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @Author:yujinqin
 * @Date:2023/1/20 10:08
 */
public class ProxyFactory {

    public static <T> T getProxy(Class interfaceClass) {
        //can choose jdk or cglib proxy

        Object proxyInstance = Proxy.newProxyInstance(ProxyFactory.class.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(), method.getParameterTypes(), args);

                HttpClient httpClient = new HttpClient();
                //通过服务注册和发现解决发请求的参数问题
//                String resp = httpClient.send("localhost", 8081, invocation);
                //这里写代码无法实现两个线程共享一个map，把这块换成zk或者redis都可以,通过zk来注册各机器(hostname,port)，然后通过接口名查询实例
                List<URL> urls = RemoteRegister.get(interfaceClass.getName());
                URL url = RandomUtils.getRandomUrl(urls);
                String resp = httpClient.send(url.getHostname(), url.getPort(), invocation);
                System.out.println("receives response:" + resp);
                return resp;
            }
        });

        return (T) proxyInstance;
    }
}
