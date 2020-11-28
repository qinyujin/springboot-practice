package com.aimer.component.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :覃玉锦
 * @create :2020-09-23 13:46:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCourse {
    private int id;
    private int userId;
    private int courseId;
}
