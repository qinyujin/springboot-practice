package com.aimer.component.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :覃玉锦
 * @create :2020-09-23 13:45:00
 */
@Data
@AllArgsConstructor
public class Course {
    private int id;
    private String name;
    private int tid;

    public Course() {
        System.out.println("空参构造课程");
    }

    public Course(String name){
        this.name=name;
    }

    public static List<Course> getCourses(){
        ArrayList<Course> courses = new ArrayList<>();
        Course c1 = new Course(1,"Java",10);
        Course c2 = new Course(2,"Web",2);
        Course c3 = new Course(3,"Python",4);
        Course c4 = new Course(4,"Json",4);
        courses.add(c1);
        courses.add(c2);
        courses.add(c3);
        courses.add(c4);
        return courses;
    }
}
