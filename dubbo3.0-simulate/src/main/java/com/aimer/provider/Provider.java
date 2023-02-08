package com.aimer.provider;

import com.aimer.framework.URL;
import com.aimer.framework.protocol.http.HttpServer;
import com.aimer.framework.register.LocalRegister;
import com.aimer.framework.register.RemoteRegister;
import com.aimer.provider.service.HelloService;
import com.aimer.provider.service.impl.HelloServiceImpl;

import java.util.Arrays;

/**
 * @Author:yujinqin
 * @Date:2023/1/19 15:53
 */
public class Provider {
    public static void main(String[] args) {
        //通过name确定唯一实例，若同一接口多实现类，通过name+version也可以确定唯一
        LocalRegister.register(HelloService.class.getName(), HelloServiceImpl.class);

        URL url = new URL("localhost", 8081);
        RemoteRegister.register(HelloService.class.getName(), Arrays.asList(url));

        //这里也可以替换成netty协议，可以把通信协议作为参数写入到dubbo framework中
        HttpServer httpServer = new HttpServer();
        httpServer.start("localhost", 8081);

//        NettyServer nettyServer = new NettyServer();
//        nettyServer.start("localhost", 8081);

    }


}
