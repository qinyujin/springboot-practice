package com.aimer.framework;

public interface Protocol {

    void export(URL url);
    Invoker refer(URL url);
}
