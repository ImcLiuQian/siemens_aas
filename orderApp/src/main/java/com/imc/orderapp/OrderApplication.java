package com.imc.orderapp;

import com.imc.siemens_aas.opcua.OpcUaClientService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"com.imc"}, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
                OpcUaClientService.class
        })
})//因为common中有写了OPC UA相关的功能，订单应用引用了common模块，但是又不需要调用OPC UA的操作，所以这里取消OpcUaClientService的自动加载
public class OrderApplication {
    public static void main(String[] args) {



        //TODO 用application.properties进行自动配置
        //TODO 完成Order的建模，增加订单的功能，生成二维码，以及完成数据的持久化
        //TODO I4.0调用消息应该在哪里写？？？后端写一个接口吧
        //TODO 添加HTTP版本的AAS的模型访问接口


        //TODO 分解订单
        //方法1 直接用随机数将订单分到CNC1 和 CNC2

        //方法2 1）把请求者状态机改成多线程的
        //     2）Submodel里面加一个ServiceDoing方法，直到检测到counter达到Order规定的数量
        //     3）要改Submodel和AAS的模型，把这个加上去

        SpringApplication.run(OrderApplication.class, args);
    }
}