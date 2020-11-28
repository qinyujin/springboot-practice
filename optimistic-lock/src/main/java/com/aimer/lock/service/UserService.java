package com.aimer.lock.service;

import com.aimer.lock.entity.User;
import com.aimer.lock.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author :覃玉锦
 * @create :2020-09-21 13:51:00
 */
@Service
@Slf4j
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public Integer updateUserById(User u){
        int retryCount = 0;
        User user = userMapper.selectById(u.getId());
        int version = user.getVersion();
        user.setName(u.getName());
        user.setPwd(u.getPwd());

        Integer integer = userMapper.updateUserById(user, version);
        while (integer==0 && retryCount<2){
            log.info("更新用户失败，用户："+user);
            integer= userMapper.updateUserById(user, version);
            retryCount++;
        }
        return integer;
    }
}
