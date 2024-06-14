package com.aimer.framework.protocol.dubbo;


import com.aimer.framework.Invoker;
import com.aimer.framework.Protocol;
import com.aimer.framework.URL;
import com.aimer.framework.register.LocalRegister;
import com.aimer.framework.register.RemoteMapRegister;

public class DubboProtocol implements Protocol {

    @Override
    public void export(URL url) {
        //实际dubbo里会先启动网络服务再注册
        LocalRegister.regist(url.getInterfaceName(), url.getImplClass()); // 接口名:实现类
        RemoteMapRegister.regist(url.getInterfaceName(), url); //接口名:url
        new NettyServer().start(url.getHostname(), url.getPort());
    }

    @Override
    public Invoker refer(URL url) {
        return new DubboInvoker(url);
    }

}
