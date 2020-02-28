package com.ssm.springboot.service;

import com.ssm.springboot.entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface UserService {

    public Map<String,Object> getAll(UserEntity entity);

    public String uploadImg(UserEntity entity, MultipartFile upload);
}
