package com.aimer.lock.mapper;

import com.aimer.lock.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;

/**
 * @author :覃玉锦
 * @create :2020-09-21 11:22:00
 */
public interface UserMapper extends BaseMapper<User> {
    @Update("update user set name = #{user.name},pwd = #{user.pwd},version = version+1 where version = #{version}")
    Integer updateUserById(User user,int version);
}
