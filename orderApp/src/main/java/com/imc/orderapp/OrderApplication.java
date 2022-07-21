package com.imc.orderapp;

import com.imc.siemens_aas.opcua.OpcUaClientService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(basePackages = {"com.imc"}, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
                OpcUaClientService.class
        })
})//因为common中有写了OPC UA相关的功能，订单应用引用了common模块，但是又不需要调用OPC UA的操作，所以这里取消OpcUaClientService的自动加载
public class OrderApplication {
    public static void main(String[] args) {


        //TODO 发送状态机的状态
        //TODO 修改spring boot启动图像
        //TODO 用application.properties进行自动配置
        //TODO 完成Order的建模，增加订单的功能，生成二维码，以及完成数据的持久化
        //TODO I4.0调用消息应该在哪里写？？？后端写一个接口吧
        //TODO 添加HTTP版本的AAS的模型访问接口

        SpringApplication.run(OrderApplication.class, args);
    }
}