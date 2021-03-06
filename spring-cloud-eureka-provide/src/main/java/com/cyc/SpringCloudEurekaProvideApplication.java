package com.cyc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @EnableDiscoveryClient ： 激活DiscoveryClient实现
 */
@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudEurekaProvideApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurekaProvideApplication.class, args);
    }
}
