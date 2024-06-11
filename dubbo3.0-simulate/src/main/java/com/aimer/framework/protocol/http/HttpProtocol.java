package com.aimer.framework.protocol.http;


import com.aimer.framework.Invoker;
import com.aimer.framework.Protocol;
import com.aimer.framework.URL;
import com.aimer.framework.register.LocalRegister;
import com.aimer.framework.register.RemoteMapRegister;

public class HttpProtocol implements Protocol {

    @Override
    public void export(URL url) {
        // 本地注册
        LocalRegister.regist(url.getInterfaceName(), url.getImplClass());
        // 注册中心注册
        RemoteMapRegister.regist(url.getInterfaceName(), url);
        // 启动Tomcat
        new HttpServer().start(url.getHostname(), url.getPort());
    }

    @Override
    public Invoker refer(URL url) {
        return new HttpInvoker(url);
    }

}
