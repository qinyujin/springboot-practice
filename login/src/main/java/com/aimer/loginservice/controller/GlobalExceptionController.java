package com.aimer.loginservice.controller;

import com.aimer.loginservice.entity.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author :覃玉锦
 * @create :2020-09-17 17:22:00
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionController {

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseBody
    public CommonResult handlerExceptionI(ResponseStatusException e){
      log.info("code:"+e.getStatus());
      log.info("code value:"+e.getStatus().value());
      log.info("message:"+e.getMessage().substring(e.getMessage().indexOf("\"")));

      int code = e.getStatus().value();
      String msg = e.getMessage().substring(e.getMessage().indexOf("\""));

      return new CommonResult(code,msg);
    }
}
