package com.Aimer.service;

import com.Aimer.UserService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @Author:yujinqin
 * @Date:2023/1/18 14:39
 * <p>
 * Service层面提供服务处理逻辑
 * @DubboService 才能使用dubbo查询到
 */
//@Service
@DubboService(version = "2.0")
public class UserServiceImpl2 implements UserService {

    public String getUser() {
        return "Aimer2";
    }
}
