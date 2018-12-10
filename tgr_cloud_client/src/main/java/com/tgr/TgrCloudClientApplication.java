package com.tgr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TgrCloudClientApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(TgrCloudClientApplication.class);
	}
}
