package com.tgr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer	//开启 EurekaServer 的功能 
@SpringBootApplication
public class TgrCloudServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TgrCloudServerApplication.class);
	}
}
