package com.cyc.provide;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by cyc_e on 2017/8/17.
 */
@Controller
@RestController
public class ProvideController {
    private final Logger logger = Logger.getLogger(ProvideController.class);

    /**
     * 客户端
     */
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/provideHello")
    public String provideHello() {
        ServiceInstance localServiceInstance = discoveryClient.getLocalServiceInstance();
        logger.info("host: " + localServiceInstance.getHost() + ", service_id : " + localServiceInstance.getServiceId());
        return "hello, provide";
    }

}
