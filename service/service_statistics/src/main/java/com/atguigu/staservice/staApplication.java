package com.atguigu.staservice;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan(basePackages = {"com.atguigu"})
@SpringBootApplication
@MapperScan("com.atguigu.staservice.mapper")
@EnableScheduling //定时需要这个注解
public class staApplication {
    public static void main(String[] args) {
        SpringApplication.run(staApplication.class,args);
    }
}
