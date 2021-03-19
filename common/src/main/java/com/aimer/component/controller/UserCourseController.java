package com.aimer.component.controller;

import com.aimer.component.dao.UserCourseDao;
import com.aimer.component.entity.UserCourseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

/**
 * @author :覃玉锦
 * @create :2020-09-23 14:07:00
 */
@RestController
@Slf4j
public class UserCourseController {

    @Autowired
    private UserCourseDao userCourseDao;

    @PostMapping("multiInsert")
    @Transactional
    public Map multiInsert(@RequestBody UserCourseVO userCourseList){
        if(userCourseList.getUserCourseList().size()==0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"参数为空！");
      log.info("传入参数："+userCourseList);
        int i = userCourseDao.multiSaveUserCourses(userCourseList.getUserCourseList());
        if(i==0)return Map.of("400","请重试！");
        else return Map.of("200","插入成功!");
    }
}
