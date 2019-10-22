package com.eureka.common.springeurekacommon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringEurekaCommonApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEurekaCommonApplication.class, args);
	}

}
