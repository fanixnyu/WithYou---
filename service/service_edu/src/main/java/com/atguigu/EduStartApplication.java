package com.atguigu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;





@SpringBootApplication
@ComponentScan(basePackages = {"com.atguigu"})
public class EduStartApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduStartApplication.class);
    }
}
