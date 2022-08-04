package com.atguigu.cannal;
import com.atguigu.cannal.client.CanalClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class StartApplication implements CommandLineRunner {

    @Resource
    private CanalClient canalClient;

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class,args);
    }




    @Override
    public void run(String... args) throws Exception {
//项目启动，执行canal客户端监听
        canalClient.run();
    }
}
