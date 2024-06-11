package com.aimer.framework.protocol.http;


import com.aimer.framework.Invocation;
import com.aimer.framework.Invoker;
import com.aimer.framework.URL;

public class HttpInvoker implements Invoker {

    private URL url;

    public HttpInvoker(URL url) {
        this.url = url;
    }

    @Override
    public String invoke(Invocation invocation) {
        HttpClient httpClient = new HttpClient();
        return httpClient.send(url.getHostname(), url.getPort(), invocation);
    }
}
