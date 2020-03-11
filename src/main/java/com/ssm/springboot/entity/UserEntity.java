package com.ssm.springboot.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserEntity implements Serializable {

    private Integer id;
    private String userName;
    private String userSex;
    private String userAge;
    private String idCard;
    private String phone;
    private Date birthday;
    private String bloodType;
    private Integer page=2;
    private Integer size=2;

}
