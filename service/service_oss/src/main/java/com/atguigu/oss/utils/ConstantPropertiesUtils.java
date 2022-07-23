package com.atguigu.oss.utils;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component//交给spring管理
public class ConstantPropertiesUtils implements InitializingBean {
//    使用@Value()注解读取properties配置文件的内容(spring包下)

    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.file.keyid}")
    private String keyId;

    @Value("${aliyun.oss.file.keysecret}")
    private String keySecret;

    @Value("${aliyun.oss.file.bucketname}")
    private String bucketName;


//    为了其他模块可以使用，定义公开静态常量
    public static String END_POINT;
    public static String KEY_ID;
    public static String KEY_SECRET;
    public static String BUCKET_NAME;


    // 实现出来 InitializingBean里面方法
    @Override
    public void afterPropertiesSet() throws Exception {
//       进行赋值
        END_POINT=endpoint;
         KEY_ID=keyId;
        KEY_SECRET=keySecret;
        BUCKET_NAME=bucketName;


    }
/**
 * 流程：
 * 当项目已启动，在spring里面有一个接口，spring加载之后，执行接口中的方法；
 * 定义公开类
 * @Value读取字段内容赋值
 */
}
