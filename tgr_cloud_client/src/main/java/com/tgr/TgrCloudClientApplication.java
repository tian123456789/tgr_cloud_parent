package com.tgr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class TgrCloudClientApplication {
	
	@Autowired
	private RestTemplateBuilder restTemplateBuilder;
	
	public static void main(String[] args) {
		SpringApplication.run(TgrCloudClientApplication.class,args);
	}
	
	public RestTemplate restTemplate() {
		return restTemplateBuilder.build();
	}
}
