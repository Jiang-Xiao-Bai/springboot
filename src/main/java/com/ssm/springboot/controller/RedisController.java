package com.ssm.springboot.controller;


import com.ssm.springboot.entity.UserEntity;
import com.ssm.springboot.utils.RedisUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.Key;
import java.util.Date;

/**
 * @program: springbootdemo
 * @Date: 2019/2/22 15:03
 * @Author: zjjlive
 * @Description:
 */
@RequestMapping("/redis")
@RestController
public class RedisController {

    private static int ExpireTime = 60;   // redis中存储的过期时间60s

    @Resource
    private RedisUtil redisUtil;

    @RequestMapping("set")
    public boolean redisset(String key,String value){
        UserEntity userEntity =new UserEntity();
        userEntity.setId(Integer.valueOf(1));
        userEntity.setUserName("张三");
        userEntity.setUserAge(20+"岁");

//        return redisUtil.set(key,userEntity,ExpireTime);

        return redisUtil.set(key,value);
    }

    @RequestMapping("get")
    public Object redisget(String key){
        return redisUtil.get(key);
    }

    @RequestMapping("expire")
    public boolean expire(String key){
        return redisUtil.expire(key,ExpireTime);
    }

    @RequestMapping("delete")
    public String delete(String...key){
        redisUtil.del(key);
        return key!=null ? "删除成功！":"删除失败！";
    }
}
