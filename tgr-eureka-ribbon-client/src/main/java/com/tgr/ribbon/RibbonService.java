package com.tgr.ribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class RibbonService {

	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * @param name
	 * @return
	 * @HystrixCommand(fallbackMethod = "hiError") 启用Hystrix熔断器的功能
	 * 	fallbackMethod为处理(fallback)逻辑的方法
	 * 这样做的好处就是通过快速失败，请求能够得到及时处理，线程不再阻塞。
	 * 
	 */
	@HystrixCommand(fallbackMethod = "hiError")
	public String hi(String name) {
		return restTemplate.getForObject("http://tgr-cloud-client/hi?name="+name,String.class);
	}
	
	public String hiError(String name) {
		return "hi :"+name+"sorry,error";
	}
	
}
