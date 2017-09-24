package com.cyc.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cyc_e on 2017/8/20.
 */
@RestController
public class FeignController {

    @Autowired
    private FeignService feignService;

    @RequestMapping(value = "/feignConsumer")
    public String feignConsumer() {
        return feignService.provideHello(1L);
    }

    @RequestMapping(value = "/provideHello")
    public String provideHello() {
        return feignService.provideHello();
    }

}
