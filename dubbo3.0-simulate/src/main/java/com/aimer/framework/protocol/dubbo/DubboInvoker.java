package com.aimer.framework.protocol.dubbo;


import com.aimer.framework.Invocation;
import com.aimer.framework.Invoker;
import com.aimer.framework.URL;

public class DubboInvoker implements Invoker {

    private URL url;

    public DubboInvoker(URL url) {
        this.url = url;
    }

    @Override
    public String invoke(Invocation invocation) {
        NettyClient nettyClient = new NettyClient();
        return nettyClient.send(url.getHostname(),url.getPort(), invocation);
    }

}
