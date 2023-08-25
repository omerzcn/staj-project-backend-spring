package com.example.thyproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ThyProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThyProjectApplication.class, args);
    }

}
