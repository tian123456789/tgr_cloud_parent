package com.tgr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RibbonConfig {

	@Autowired
	private RestTemplateBuilder restTemplateBuildder;
	
	@Bean
	@LoadBalanced//Annotation to mark a RestTemplate bean to be configured to use a LoadBalancerClient
				 //springCloud注解 
				 //Spring Cloud的commons模块提供了一个@LoadBalanced注解，方便我们对RestTemplate添加一个LoadBalancerClient，
				 //以实现客户端负载均衡。通过源码可以发现这是一个标记注解：
				 //https://blog.csdn.net/Tincox/article/details/79210309
	RestTemplate restTemplate() {
		return restTemplateBuildder.build();
	}
}
