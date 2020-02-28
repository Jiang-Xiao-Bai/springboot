package com.ssm.springboot.mapper;

import com.ssm.springboot.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    public List<UserEntity> getAll(UserEntity entity);

    public int addImg(UserEntity entity);
}
