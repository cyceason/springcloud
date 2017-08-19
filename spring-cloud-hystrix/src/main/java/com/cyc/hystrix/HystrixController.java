package com.cyc.hystrix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cyc_e on 2017/8/19.
 */
@RestController
public class HystrixController {

    @Autowired
    private HystrixService hystrixService;

    @RequestMapping(value = "ribbon-consumer")
    public String hystrixConsumer() {
        return hystrixService.hystrixService();
    }


}
