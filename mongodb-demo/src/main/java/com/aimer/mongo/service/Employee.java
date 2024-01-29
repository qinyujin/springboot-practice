package com.aimer.mongo.service;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * @author :覃玉锦
 * @create :2024-01-22 22:03:00
 * @Document 修饰范围: 用在类上
 * 作用: 用来映射这个类的一个对象为mongo中一条文档数据。
 * 属性:( value 、collection )用来指定操作的集合名称
 * @Id 修饰范围: 用在成员变量、方法上
 * 作用: 用来将成员变量的值映射为文档的_id的值
 * @Field 修饰范围: 用在成员变量、方法上
 * 作用: 用来将成员变量及其值映射为文档中一个key:value对。
 * 属性:( name , value )用来指定在文档中 key的名称,默认为成员变量名
 * @Transient 修饰范围:用在成员变量、方法上
 * 作用:用来指定此成员变量不参与文档的序列化
 */
@Document("emp")  //对应emp集合中的一个文档
public class Employee {

    @Id   //映射文档中的_id
    private Integer id;

    @Field("username")
    private String name;

    @Field
    private int age;

    @Field
    private Double salary;

    @Field
    private Date birthday;

    public Employee() {
    }

    public Employee(Integer id, String name, int age, Double salary, Date birthday) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.birthday = birthday;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", birthday=" + birthday +
                '}';
    }
}
