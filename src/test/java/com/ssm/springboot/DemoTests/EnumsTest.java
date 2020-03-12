package com.ssm.springboot.DemoTests;

import sun.reflect.CallerSensitive;
import sun.reflect.ConstantPool;
import sun.reflect.annotation.AnnotationType;

import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.net.URL;
import java.security.ProtectionDomain;
import java.util.Map;

/**
 * @author zhaohf
 * @date 2020/3/12 15:01
 */
public enum  EnumsTest {
    student(12,"张三","男"), teacher(54,"荻花圣殿","不是你"), computer(45,"vgzv=","xh");
    private int age;
    private String name;
    private String sex;

    EnumsTest(int age, String name, String sex) {
        this.age = age;
        this.name = name;
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return "EnumsTest{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }

    public static void main(String[] args) {
        System.out.println(EnumsTest.student.getName());
    }
}
