package com.imc.service_cnc1_copy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"com.imc"})
public class CNC1BackUpApplication {
    public static void main(String[] args) {
        SpringApplication.run(CNC1BackUpApplication.class, args);
    }
}
