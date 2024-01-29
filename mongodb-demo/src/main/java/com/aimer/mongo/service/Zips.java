package com.aimer.mongo.service;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author :覃玉锦
 * @create :2024-01-22 22:34:00
 */
@Document
public class Zips {

    @Id
    private String id;

    @Field
    private String city;

    @Field
    private Double[] loc;

    @Field
    private Integer pop;

    @Field
    private String state;
}
