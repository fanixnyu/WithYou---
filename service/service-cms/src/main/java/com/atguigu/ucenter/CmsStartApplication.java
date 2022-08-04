package com.atguigu.ucenter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@MapperScan(basePackages = "com.atguigu.educms.mapper")
public class CmsStartApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsStartApplication.class,args);
    }
}
