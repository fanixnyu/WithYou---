package com.atguigu.oss.controller;
import com.atguigu.common_utils.R;
import com.atguigu.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("eduoss/fileoss")
public class OssController {

    @Autowired
    private OssService ossService;

   @PostMapping("uploadOssFile")
   //    上传文件使用MultipartFile
    public R uploadOssFile(MultipartFile file){
//返回的路径是存入数据库表中的字段Avatar 存入Oss的网址  数据类型
    String url =ossService.uploadFileAvatar(file);
       return R.ok().data("url",url);
   }

}
