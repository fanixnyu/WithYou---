package com.atguigu.msmservice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan("com.atguigu")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MsmStartApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsmStartApplication.class,args);
    }
}
