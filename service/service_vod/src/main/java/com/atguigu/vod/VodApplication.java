package com.atguigu.vod;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

//exclude 不准
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//只对视频操作，不操作数据库，防止数据库那边报错
@ComponentScan(basePackages = "com.atguigu")
//@EnableDiscoveryClient
public class VodApplication {
    public static void main(String[] args) {
        SpringApplication.run(VodApplication.class,args);

    }
}
