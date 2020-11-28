package com.aimer.loginservice.controller;

import com.aimer.component.util.RedisUtil;
import com.aimer.loginservice.component.MyToken;
import com.aimer.loginservice.component.RSAUtil;
import com.aimer.loginservice.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author :覃玉锦
 * @create :2020-08-11 16:10:00
 */
@RestController
@CrossOrigin
@Slf4j
public class LoginController {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ObjectMapper objectMapper;

    private String privateKey="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIdAybCfMJpUb6+8zVgm/MvESQoU9R4yqdMIbDXg/7QCFKYImWcrQ9AZon3Eq72rR5s7i6M/5r8i8Qrl5NSZfaJwerJ91dbNXfbsKhg7cYwoRd6WCNkdaDlbPiG5Qu4O8fHbk2Km8Wu/GfHsa4MjPBN7g31nhKiVA4IuMMZ8AL4rAgMBAAECgYA7anSvyF7bjTg7JV+31/i5sxK7ARXlHDCKamrPCHaMJ1XW0nRBZDo4EurVAS7I9ZNRWEkxgALn+HuXZAKwenU733U4ZS7O4MMu9jRS2otGlzk201BeRHwA3vE9e7SZOCTjb899d9E7L6eYkzcf8CrWALxsXxaMYWkzq6mvxfQpYQJBANi6/b9uquKSIA+dWaMyVYphK18yQ/i+JT1RHCA1WOoDXGiMGqCiwbV6ING/29T+VazatRbUBNX8kdQCkvcsXEkCQQCfwntMSlxq9vM27ybIfl8x//ZhJ0Wovk3+yQFtkhCYB1xNo2m/yaYrrHD+E5u3f1zaW3vLkaWdJwLyU/GdXz7TAkEAoFl48/GFLSy/282nTD8pWsCZvk0KTTchhkREcvAFYN4ruI4wBTll6Eb7kTRKCURDTyhhYwpqqYe8vQdKhOWlQQJBAJqjWU2DPOmcl9c4rcWr7OEs7YMkzFyFuGFCl0tD8yP1G/dakeW+fkYP6F9HA5cehPYnTzDAjhoLxOJjNqAprVkCQDq/dmSSVgnE5K7bJJeECd0jr0XTb57+EFaggaiPq6kjUD3v/VJnoLinOpuRzkKLAsQfz3Rp35dLa6xfsKwpIUQ=\n" +
            "df723820";


    @PostMapping("login")
    public Map login(HttpServletResponse response,@RequestBody User user) throws JsonProcessingException {
        //用户校验通过，将用户所需信息写入jwt传输,登录校验成功将jwt传给客户端，客户端下次请求在header里带上
        //jwt，在网关做登录权限校验。
        if(user.getName().equals("Qin") && user.getPassword().equals("123456")){
            User u = new User();
            u.setName(user.getName());
            u.setPassword(user.getPassword());
            ArrayList<Integer> list = new ArrayList<>();
            list.add(1);
            list.add(2);
            MyToken myToken = new MyToken(2, list, 53778);
            String json = objectMapper.writeValueAsString(myToken);
            log.info("MyToken:"+json);
            String authorization = JWT.create().withAudience(json)
                    .sign(Algorithm.HMAC256(privateKey));
            log.info("jwt:"+authorization);
            response.setHeader("authorization", authorization);
            log.info("header:"+response.getHeader("authorization"));
            return Map.of("user msg",authorization);
        }
        return Map.of("user",user);
    }

    @PostMapping("encry/login")
    public Map encryptLogin(@RequestBody User user) throws Exception {
        log.info("请求体数据："+user);
        String decrypt = RSAUtil.decrypt(user.getPassword(), privateKey);
        log.info("解密的密码："+decrypt);
        if("123456".equals(decrypt)){
            return Map.of("登录成功！","null");
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"密码错误");
    }

    @GetMapping("test")
    public Map test(HttpServletRequest request){
        return Map.of("auth",request.getHeader("authorization"));
    }

    @GetMapping("test1")
    public Map test1(){
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"登录模块抛出的异常");
    }

}
