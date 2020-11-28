package com.regex.entity;

import lombok.Data;

import javax.validation.constraints.Pattern;

/**
 * @author :覃玉锦
 * @create :2020-09-29 06:36:00
 */
@Data
public class Url {
    //校验restful风格url
    @Pattern(regexp = "\\/api(\\/[^\\/][a-z-\\d]*)+",message = "以/api/开头,只能有小写字母、-、数字")
    private String url;
}
