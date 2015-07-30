package com.ssrolc.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.ssrolc")
public class SsrolcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsrolcApplication.class, args);
    }
}
