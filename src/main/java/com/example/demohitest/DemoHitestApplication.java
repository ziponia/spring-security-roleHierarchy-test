package com.example.demohitest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableMethodSecurity
@SpringBootApplication
public class DemoHitestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoHitestApplication.class, args);
    }

}
