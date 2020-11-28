package com.aimer.loginservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :覃玉锦
 * @create :2020-09-17 17:23:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private int code;
    private String message;
    private T data;

    public CommonResult(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
