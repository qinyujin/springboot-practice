package com.regex.controller;

import com.regex.entity.Url;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author :覃玉锦
 * @create :2020-09-29 06:38:00
 */
@RestController
@Slf4j
public class UrlController {
    @PostMapping("url")
    public Map setUrl(@Valid @RequestBody Url url){
      log.info("测试一下url的正则是否是restful风格的");
        String regex = "-*\\d{1,}";
      return Map.of("成功",url.getUrl().replaceAll(regex,"{id}"));
    }
}
