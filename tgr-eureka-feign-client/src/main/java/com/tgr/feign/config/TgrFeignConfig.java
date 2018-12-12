package com.tgr.feign.config;


import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Retryer;

@Configuration
public class TgrFeignConfig {

	/**
	 * @return
	 * 远程调用失败后进行重试
	 * 重试问隔为 100 毫秒,最大重试时间为 1 秒,重试次数为 5 次。
	 */
	@Bean
	public Retryer feignRetryer() {
		return new Retryer.Default(100,TimeUnit.SECONDS.toMillis(1),5);
	}
		
		/*源码
		period:时期,一段时间
		public Default() {
	      this(100, SECONDS.toMillis(1), 5);
	    }

	    public Default(long period, long maxPeriod, int maxAttempts) {
	      this.period = period;
	      this.maxPeriod = maxPeriod;
	      this.maxAttempts = maxAttempts;
	      this.attempt = 1;
	    }*/
}
