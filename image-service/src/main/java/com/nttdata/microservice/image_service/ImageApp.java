package com.nttdata.microservice.image_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ImageApp {
    public static void main(String[] args) {
        SpringApplication.run(ImageApp.class, args);
    }
}