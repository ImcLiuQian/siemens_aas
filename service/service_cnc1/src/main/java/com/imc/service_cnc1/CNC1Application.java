package com.imc.service_cnc1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"com.imc"})
public class CNC1Application {
    public static void main(String[] args) {
        SpringApplication.run(CNC1Application.class, args);
    }
}
