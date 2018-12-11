package com.tgr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.client.RestTemplate;

@SuppressWarnings("all")
@SpringBootApplication
@EnableEurekaClient
public class TgrEurekaRibbonClientApplication {

	/*@Autowired
	private RestTemplateBuilder restTemplateBuilder;*/
	
	public static void main(String[] args) {
		SpringApplication.run(TgrEurekaRibbonClientApplication.class,args);
	}
	
	/*public RestTemplate restTemplate() {
		return restTemplateBuilder.build();
	}*/
}
