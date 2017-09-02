package com.cyc;

import com.cyc.core.ListServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringbootApplication {

    @Autowired
    ListServer listServer;

    @Autowired
    HelloService helloService;

    @RequestMapping("/")
    public String home() {
        return listServer.showListCmd();
    }

    @RequestMapping("/hello")
    public String hello() {
        return helloService.getMsg();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }
}
