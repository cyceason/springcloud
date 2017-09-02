package com.cyc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(HelloServiceProperties.class) // 开启属性注入
@ConditionalOnClass(HelloService.class) // 当HelloService在类路径的条件下
@ConditionalOnProperty(prefix = "hello", value = "enabled", matchIfMissing = true) // 当hello=enable的情况下，如果没有设置则默认为true，即条件符合
public class HelloServiceAutoConfiguration {
	@Autowired
	private HelloServiceProperties helloServiceProperties;// 注入HelloServiceProperties

	/**
	 * 当容器没有HelloService时，新建HelloService
	 * 
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(HelloService.class)
	public HelloService helloService() {
		HelloService helloService = new HelloService();
		helloService.setMsg(helloServiceProperties.getMsg());
		return helloService;
	}
}
