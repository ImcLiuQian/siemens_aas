package com.imc.service_cnc1_copy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.imc"})
public class CNC1CopyApplication {
    public static void main(String[] args) {
        SpringApplication.run(CNC1CopyApplication.class, args);
    }
}