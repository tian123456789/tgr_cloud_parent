package com.tgr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tgr.feign.service.TgrEurekaClientFeign;

@Service
public class HiService {

	@Autowired
	private TgrEurekaClientFeign tgrEurekaClientFeign;
	
	public String sayHi(String name) {
		return tgrEurekaClientFeign.sayHiFromClientEUreka(name);
	}
}
