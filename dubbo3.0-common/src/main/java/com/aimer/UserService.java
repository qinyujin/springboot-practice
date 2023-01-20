package com.aimer;

import org.apache.dubbo.common.stream.StreamObserver;

/**
 * 接口列表，对于所有接入dubbo的服务进行展示。服务间可从这里调用、实现、查询
 * <p>
 * triple协议包含的调用方式：1、unary 2、server_stream 3、client_stream/bi_stream
 */
public interface UserService {
    //unary
    String getUser();

    //server_stream
    default void sayHelloServerStream(String name, StreamObserver<String> response) {
    }

    //client_stream
    default StreamObserver<String> sayHelloClientStream(StreamObserver<String> response) {
        return response;
    }
}
