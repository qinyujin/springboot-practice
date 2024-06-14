package com.aimer.framework;

public interface Protocol {

    //服务导出
    void export(URL url);
    //服务引入
    Invoker refer(URL url);
}
