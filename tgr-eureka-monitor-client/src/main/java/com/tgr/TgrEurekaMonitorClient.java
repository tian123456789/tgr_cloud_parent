package com.tgr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

//http://127.0.0.1:8765/hystrix
@SpringBootApplication
@EnableHystrixDashboard
@EnableTurbine//这都能忘，我也是醉了
public class TgrEurekaMonitorClient {

	public static void main(String[] args) {
		SpringApplication.run(TgrEurekaMonitorClient.class, args);
	}
}
