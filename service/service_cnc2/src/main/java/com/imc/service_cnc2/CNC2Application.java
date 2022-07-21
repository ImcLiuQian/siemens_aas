package com.imc.service_cnc2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"com.imc"})
public class CNC2Application {
    public static void main(String[] args) {
        SpringApplication.run(CNC2Application.class, args);
    }
}
