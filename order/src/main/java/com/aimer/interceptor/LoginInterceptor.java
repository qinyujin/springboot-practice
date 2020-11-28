package com.aimer.interceptor;

import com.aimer.entity.User;
import com.aimer.util.RedisUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author :覃玉锦
 * @create :2020-08-11 19:57:00
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisUtil util;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = util.get("user");
        User u = objectMapper.readValue(String.valueOf(user), User.class);
        log.info("进入拦截器");
        if(user!=null){
            log.info("用户已经登录");
            request.setAttribute("name",u.getName() );
            request.setAttribute("pwd", u.getPassword());
        }
        else {
            log.info("用户未登录");
            throw  new ResponseStatusException(HttpStatus.UNAUTHORIZED,"未登录");
        }
        log.info("通过拦截器");

        return true;
    }
}
