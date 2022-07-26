package com.atguigu.educms;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.atguigu.educms.mapper")
public class CmsStartApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsStartApplication.class,args);
    }
}
