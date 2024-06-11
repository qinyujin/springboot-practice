package com.aimer.framework.protocol.dubbo;


import com.aimer.framework.Invoker;
import com.aimer.framework.Protocol;
import com.aimer.framework.URL;
import com.aimer.framework.register.LocalRegister;
import com.aimer.framework.register.RemoteMapRegister;

public class DubboProtocol implements Protocol {

    @Override
    public void export(URL url) {
        LocalRegister.regist(url.getInterfaceName(), url.getImplClass());
        RemoteMapRegister.regist(url.getInterfaceName(), url);
        new NettyServer().start(url.getHostname(), url.getPort());
    }

    @Override
    public Invoker refer(URL url) {
        return new DubboInvoker(url);
    }

}
