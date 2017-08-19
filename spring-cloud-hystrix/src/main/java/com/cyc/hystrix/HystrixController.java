package com.cyc.hystrix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * Created by cyc_e on 2017/8/19.
 */
@RestController
public class HystrixController {

    @Autowired
    private HystrixService hystrixService;

    @RequestMapping(value = "ribbon-consumer")
    public User hystrixConsumer() {
        return hystrixService.hystrixService();
    }

    @RequestMapping(value = "ribbon-consumer-async")
    public User hystrixConsumerAsync() throws ExecutionException, InterruptedException {
        return hystrixService.hystrixServiceAsync().get();
    }

    @RequestMapping(value = "ribbon-consumer-exception")
    public User hystrixConsumerException() throws ExecutionException, InterruptedException {
        return hystrixService.hystrixServiceException();
    }

}
