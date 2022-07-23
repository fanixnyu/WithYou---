package com.atguigu.vod.Utils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component //通过spring的注解读取properties配置属性
public class ConstantVodUtils implements InitializingBean {

//    使用@Value注解获取配置文件属性
    @Value("${aliyun.vod.file.keyid}")
    private String keyid;

    @Value("${aliyun.vod.file.keysecret}")
    private String keysecret;

//  定义公开常量类
    public static String ACCESS_KEY_SECRET;
    public static String ACCESS_KEY_ID;


    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_ID =keyid;
        ACCESS_KEY_SECRET =keysecret;
    }
}
