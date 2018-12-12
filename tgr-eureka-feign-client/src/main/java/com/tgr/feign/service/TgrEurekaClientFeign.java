package com.tgr.feign.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tgr.feign.config.TgrFeignConfig;

/**
 * @author tgr
 *	@FeignClient 声明一个Feign Client
 *	value为远程调用其他服务的服务名
 *	configuration为Feign Client的配之类
 */
@FeignClient(value = "tgr-cloud-client",configuration = TgrFeignConfig.class)
public interface TgrEurekaClientFeign {

	/**
	 * @param name
	 * @return
	 * 
	 * 调用tgr-cloud-client服务的服务
	 */
	@GetMapping(value = "/hi")
	String sayHiFromClientEUreka(@RequestParam(value = "name") String name);
}
