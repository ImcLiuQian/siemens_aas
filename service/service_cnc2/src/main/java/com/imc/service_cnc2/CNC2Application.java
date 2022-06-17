package com.imc.service_cnc2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.imc"})
public class CNC2Application {
    public static void main(String[] args) {
        SpringApplication.run(CNC2Application.class, args);
    }
}
