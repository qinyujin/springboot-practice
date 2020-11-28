package com.aimer.component.dao;

import com.aimer.component.entity.UserCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2020-09-23 13:47:00
 */
public interface UserCourseDao extends BaseMapper<UserCourse> {

    @Insert({
            "<script>",
            "insert into user_course(id, user_id, course_id) values ",
            "<foreach collection='userCourseList' item='item' index='index' separator=','>",
            "(#{item.id}, #{item.userId}, #{item.courseId})",
            "</foreach>",
            "</script>"
    })
    int multiSaveUserCourses(@Param(value = "userCourseList") List<UserCourse> userCourseList);
}
