package com.imc.service_as;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"com.imc"})
public class AsApplication {
    public static void main(String[] args) {
        SpringApplication.run(AsApplication.class, args);
    }
}
