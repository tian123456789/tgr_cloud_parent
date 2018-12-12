package com.tgr.hystrix;

import org.springframework.stereotype.Component;

import com.tgr.feign.service.TgrEurekaClientFeign;

/**
 * @author tgr
 *熔断器的逻辑处理类 需要实现自己的feign client
 */
@Component
public class HiHystrix implements TgrEurekaClientFeign {

	@Override
	public String sayHiFromClientEUreka(String name) {
		return "hi,"+name+",sorry,error!";
	}

}
