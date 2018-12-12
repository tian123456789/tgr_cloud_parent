package com.tgr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.client.RestTemplate;

@SuppressWarnings("all")
@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@EnableHystrixDashboard//开启 Hystrix Dashboard功能 此功能三个必备依赖
																	//1.spring-cloud-starter-hystrix
																	//2.spring-cloud-starter-hystrix-dashboard
																	//3.pring-boot-starter-actuator
//2018-12-12 16:37:31.256  INFO 13128 --- [           main] o.s.b.a.e.mvc.EndpointHandlerMapping     : Mapped "{[/hystrix.stream/**]}" onto public org.springframework.web.servlet.ModelAndView org.springframework.cloud.netflix.endpoint.ServletWrappingEndpoint.handle(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse) throws java.lang.Exception
//localhost:8765/hystrix再填写如下 继而请求进入
//localhost:8765/hystrix.strem
//该图来自于 Hystrix 的官方文档，更多信息可以查阅官方文档，文档地址 ： h即s://github.com/Ne国ix/		p139
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
