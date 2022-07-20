package com.imc.orderapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.imc"})
public class OrderApplication {
    public static void main(String[] args) {


        //TODO 发送状态机的状态
        //TODO AAS初始化时，绑定modelObject
        //TODO 在服务中把参数改成Integer


        SpringApplication.run(OrderApplication.class, args);
    }
}