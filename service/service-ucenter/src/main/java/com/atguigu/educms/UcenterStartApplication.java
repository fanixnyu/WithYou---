package com.atguigu.educms;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.atguigu.educms.mapper")
public class UcenterStartApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterStartApplication.class);
    }
}
