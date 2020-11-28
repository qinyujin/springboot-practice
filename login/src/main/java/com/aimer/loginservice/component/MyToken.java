package com.aimer.loginservice.component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2020-08-11 19:22:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyToken {
    private int uid;

    private List<Integer> rids;

    private int workId;
}
