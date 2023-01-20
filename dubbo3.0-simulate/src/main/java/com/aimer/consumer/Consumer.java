package com.aimer.consumer;

import com.aimer.framework.ProxyFactory;
import com.aimer.provider.service.HelloService;

/**
 * @Author:yujinqin
 * @Date:2023/1/19 15:53
 */
public class Consumer {
    public static void main(String[] args) {
        //http method
        /*Invocation invocation = new Invocation(HelloService.class.getName(), "sayHello", new Class[]{String.class}, new Object[]{"xxxtention"});

        HttpClient httpClient = new HttpClient();
        String resp = httpClient.send("localhost", 8081, invocation);
        System.out.println("receives response:" + resp);*/

        //rpc method
        HelloService proxyService = ProxyFactory.getProxy(HelloService.class);
        String resp = proxyService.sayHello("xxx");
        System.out.println(resp);
    }
}
