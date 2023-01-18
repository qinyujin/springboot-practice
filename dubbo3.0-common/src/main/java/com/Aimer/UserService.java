package com.Aimer;

//接口列表，对于所有接入dubbo的服务进行展示。服务间可从这里调用、实现、查询
public interface UserService {
    String getUser();
}
