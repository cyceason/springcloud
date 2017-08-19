package com.cyc.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * Created by cyc_e on 2017/8/19.
 */
@Service
public class HystrixService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "errorBack")
    public String hystrixService() {
        return restTemplate.getForEntity("http://SPRING-CLOUD-PROVIDER/provideHello", String.class).getBody();
    }

    public String errorBack() {
        return "error";
    }
}
