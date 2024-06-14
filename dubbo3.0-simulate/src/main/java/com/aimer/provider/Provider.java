package com.aimer.provider;

import com.aimer.framework.Protocol;
import com.aimer.framework.ProtocolFactory;
import com.aimer.framework.URL;
import com.aimer.provider.service.HelloService;
import com.aimer.provider.service.impl.HelloServiceImpl;

/**
 * @Author:yujinqin
 * @Date:2023/1/19 15:53
 */
public class Provider {
    public static void main(String[] args) {
        //配置文件中读取使用的协议，比如dubbo、http
        String protocolName = System.getProperty("protocol");

        URL url = new URL(protocolName, "localhost", 8080, HelloService.class.getName(), HelloServiceImpl.class);

        //协议会注册到本地注册中心、远程注册中心、启动网络服务
        Protocol protocol = ProtocolFactory.getProtocol(protocolName);
        protocol.export(url);

    }


}
