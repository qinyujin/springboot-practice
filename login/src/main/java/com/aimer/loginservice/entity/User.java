package com.aimer.loginservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :覃玉锦
 * @create :2020-08-11 16:00:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;

    private String password;
}
