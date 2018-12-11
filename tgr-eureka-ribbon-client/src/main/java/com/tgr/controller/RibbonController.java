package com.tgr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.ServiceInstanceChooser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tgr.ribbon.RibbonService;

@RestController
public class RibbonController {

	@Autowired
	private RibbonService ribbonService;
	
	@Autowired
	private LoadBalancerClient loadBalancerClient;//负载均衡客户端 来模拟一下负载均衡获取客户端
	
	@GetMapping("/hi")
	public String hi(@RequestParam(required = false,
						defaultValue = "forezp") String name) {
		//public interface LoadBalancerClient extends ServiceInstanceChooser 
		
		/*public interface ServiceInstanceChooser {*/

		/**
		 * Choose a ServiceInstance from the LoadBalancer for the specified service
		 * @param serviceId the service id to look up the LoadBalancer
		 * @return a ServiceInstance that matches the serviceId
		 */
		   /* ServiceInstance choose(String serviceId);*/

		return ribbonService.hi(name);
	}
	
	/********************源码******************************************/
	
	//public interface LoadBalancerClient extends ServiceInstanceChooser 
	
	/*public interface ServiceInstanceChooser {*/

	/**
	 * Choose a ServiceInstance from the LoadBalancer for the specified service
	 * @param serviceId the service id to look up the LoadBalancer
	 * @return a ServiceInstance that matches the serviceId
	 */
	/* ServiceInstance choose(String serviceId);*/
	
	/********************源码******************************************/
	
	@GetMapping("/chooseService")
	public String chooseService() {
		ServiceInstance instance = loadBalancerClient.choose("tgr-cloud-client");
		return instance.getHost()+":"+instance.getPort();
	}

}
