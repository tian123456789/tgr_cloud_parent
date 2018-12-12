package com.tgr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients

//监控熔断器以下两个就都需要啦
@EnableHystrixDashboard
@EnableHystrix
public class TgrEurekaFeignClient {
	
	public static void main(String[] args) {
		SpringApplication.run(TgrEurekaFeignClient.class, args);
	}
}
