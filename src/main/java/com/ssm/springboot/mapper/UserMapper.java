package com.ssm.springboot.mapper;

import com.ssm.springboot.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<UserEntity> getAll(UserEntity entity);

    boolean addUser(UserEntity entity);

}
