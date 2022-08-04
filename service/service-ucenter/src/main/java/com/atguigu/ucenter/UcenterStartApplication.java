package com.atguigu.ucenter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

//@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.atguigu.educms.mapper")
@ComponentScan(basePackages = "com.atguigu")
public class UcenterStartApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterStartApplication.class);
    }
}
