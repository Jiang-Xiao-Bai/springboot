package com.ssm.springboot.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ssm.springboot.entity.UserEntity;
import com.ssm.springboot.mapper.UserMapper;
import com.ssm.springboot.service.UserService;
import com.ssm.springboot.utils.StringUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServcieImpl implements UserService {

    @Autowired
    UserMapper userMapper;


    @Override
    public Map<String,Object> getAll(UserEntity entity) {
        Page page= PageHelper.startPage(entity.getPage(),entity.getSize());
        List<UserEntity> list=userMapper.getAll(entity);
        //noinspection AlibabaCollectionInitShouldAssignCapacity
        Map<String,Object> map=new HashMap<>();
        map.put("page",page);
        map.put("list",list);
        return map;
    }

    @Override
    public String uploadImg(UserEntity entity, MultipartFile upload) {
        if (upload==null){
            return "请选择文件";
        }
        if (upload!=null && !StringUtils.isEmpty(upload.getOriginalFilename())){
            String oldName=upload.getOriginalFilename();
            String[] fileArr=oldName.split("\\.");
            String fileExtention=fileArr[fileArr.length-1].toLowerCase();
            String newName=StringUtils.randomString(8)+"."+fileExtention;
            String realPath=System.getProperty("user.dir").replace("springboot","webapps/adv");
            File newFile=new File(realPath+"/"+newName);
            if(!newFile.getParentFile().exists()){
                boolean mkFile=newFile.getParentFile().mkdirs();
                if(!mkFile){
                    return "上传失败!";
                }
            }
            String path=newFile.getPath();
            System.out.println(path+"--------------------------------------");
            try {
                FileUtils.copyInputStreamToFile(upload.getInputStream(),newFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "上传成功！";
    }
}