package com.Aimer.service;

//使用的是common模块中的类
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
@DubboService(version = "1.0")
public class UserServiceImpl implements UserService {

    public String getUser() {
        return "Aimer";
    }
}
