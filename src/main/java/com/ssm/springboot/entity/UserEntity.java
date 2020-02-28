package com.ssm.springboot.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserEntity implements Serializable {

    private Integer id;
    private String name;
    private String sex;
    private Integer age;
    private String img;
    private Integer page=2;
    private Integer size=2;

}
