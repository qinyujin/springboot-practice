package com.boss.vertifier.controller;

import com.boss.vertifier.entity.VerifyCode;
import com.boss.vertifier.util.IVerifyCodeGen;
import com.boss.vertifier.util.impl.SimpleCharVerifyCodeGenImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author :覃玉锦
 * @create :2020-09-11 10:19:00
 */
@RestController
@Slf4j
public class VertifyController {
    @GetMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
        Jedis jedis = new Jedis();
        jedis.auth("123456");
        IVerifyCodeGen iVerifyCodeGen = new SimpleCharVerifyCodeGenImpl();
        try {
            //设置长宽
            VerifyCode verifyCode = iVerifyCodeGen.generate(80, 28);
            String code = verifyCode.getCode();
            //把验证码放入到redis中
            jedis.setex("VerifyCode", 60, code);

            log.info(code);
//            //将VerifyCode绑定session
//            request.getSession().setAttribute("VerifyCode", code);
//            //设置响应头
//            response.setHeader("Pragma", "no-cache");
//            //设置响应头
//            response.setHeader("Cache-Control", "no-cache");
//            //在代理服务器端防止缓冲
//            response.setDateHeader("Expires", 0);
//            //设置响应内容类型
//            response.setContentType("image/jpeg");
            response.getOutputStream().write(verifyCode.getImgBytes());
            response.getOutputStream().flush();
        } catch (IOException e) {
            log.info("", e);
        }
    }
}
