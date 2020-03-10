package com.ssm.springboot.controller;

import com.ssm.springboot.entity.UserEntity;
import com.ssm.springboot.service.UserService;
import com.ssm.springboot.utils.ResultUtil;
import com.ssm.springboot.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/test")
public class HelloController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping("/hello")
    public ResultVO hello(UserEntity entity) {
        Map<String,Object> map=userService.getAll(entity);
        return ResultUtil.success(map.get("page"),map.get("list"));
    }

    @RequestMapping("/uploadImg")
    @ResponseBody
    public String uploadImg(UserEntity entity, MultipartFile upload) {
        return userService.uploadImg(entity,upload);
    }

    @RequestMapping("/downImg")
    @ResponseBody
    public String downloadOne(HttpServletResponse response){
        String fileName = "新建文本文档.txt";// 设置文件名，根据业务需要替换成要下载的文件名
        if (fileName != null) {
            //设置文件路径
            String realPath = "C://Users//F7090//Desktop//";//文件所在的路径
            File file = new File(realPath , fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);//文件输入流
                    bis = new BufferedInputStream(fis);//字符输入流
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);//写入
                        i = bis.read(buffer);
                    }
                    System.out.println("success");//下载文件成功
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {//关闭两个流
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            return "下载失败！";
        }
        return "下载成功！";
    }

    @RequestMapping("/uploadFiles")
    @ResponseBody
    public String uploadFiles(HttpServletRequest request){
        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    String uploadFilePath = file.getOriginalFilename();
                    System.out.println("uploadFlePath:" + uploadFilePath);
                    // 截取上传文件的文件名
                    String uploadFileName = uploadFilePath
                            .substring(uploadFilePath.lastIndexOf('\\') + 1,
                                    uploadFilePath.indexOf('.'));
                    System.out.println("multiReq.getFile()" + uploadFileName);
                    // 截取上传文件的后缀
                    String uploadFileSuffix = uploadFilePath.substring( 
                            uploadFilePath.indexOf('.') + 1, uploadFilePath.length());
                    System.out.println("uploadFileSuffix:" + uploadFileSuffix);
                    stream = new BufferedOutputStream(new FileOutputStream(new File(
                             " "+uploadFileName + "." + uploadFileSuffix)));//填写需上传文件的路径
                    byte[] bytes = file.getBytes();
                    stream.write(bytes,0,bytes.length);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (stream != null) {
                            stream.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.println("上传文件为空");
            }
        }
        return "上传多个文件成功！";
    }
}
