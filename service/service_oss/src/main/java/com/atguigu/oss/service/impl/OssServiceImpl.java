package com.atguigu.oss.service.impl;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.atguigu.oss.service.OssService;
import com.atguigu.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {

                // 用工具类获取方便些(绑定的properties文件里面)
                String endpoint = ConstantPropertiesUtils.END_POINT;
                String accessKeyId = ConstantPropertiesUtils.KEY_ID;
                String accessKeySecret = ConstantPropertiesUtils.KEY_SECRET;
                String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        try {
                // 创建OSSClient实例
                OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            //    获取上传文件输入流
            InputStream inputStream= file.getInputStream();
//            获取文件名称
            String originalFilename = file.getOriginalFilename();

/**
 *当上传文件名相同，则覆盖
 * 这时候用uuid生成随机唯一的值
 *上传默认会有“-“，所有使用字符串替换掉
 */
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
            originalFilename=uuid+originalFilename;

//            把文件按照日期进行分类(pom引入了日期jar包)
            String datePath= new DateTime().toString("yyyy/MM/dd");
//            拼接
            originalFilename=datePath+"/"+originalFilename;
            ossClient.putObject(bucketName,originalFilename,inputStream);
            ossClient.shutdown();
            String url ="https://"+bucketName+"."+endpoint+"/"+originalFilename;
            return url;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

}
}
