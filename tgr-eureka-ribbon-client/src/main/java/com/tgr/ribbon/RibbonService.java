package com.tgr.ribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RibbonService {

	@Autowired
	private RestTemplate restTemplate;
	
	public String hi(String name) {
		return restTemplate.getForObject("http://tgr-cloud-client/hi?name="+name,String.class);
	}
}
