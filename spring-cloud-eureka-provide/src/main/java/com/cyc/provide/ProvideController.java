package com.cyc.provide;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


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
    public String provideHello(HttpServletResponse response, HttpServletRequest request) {
        // 用于校验能否拿到zuul的RequestContext.getCurrentContext().addZuulRequestHeader的值
        String xxxRequestHead = request.getHeader("xxxRequestHead");
        System.out.println(xxxRequestHead);
        // 用于校验能否传递给zuul的RequestContext.getCurrentContext().getZuulResponseHeaders()
        response.setHeader("xxxResponseHead", "xxxResponseHead");
        // 用于校验zuul是否能获取到对应的cookie
        response.addCookie(new Cookie("name", "cyc"));
        ServiceInstance localServiceInstance = discoveryClient.getLocalServiceInstance();
        logger.info("host: " + localServiceInstance.getHost() + ", service_id : " + localServiceInstance.getServiceId());
        return "hello, provide";
    }

    @RequestMapping(value = "/provideUser")
    public User provideUser() {
        return new User();
    }

    @RequestMapping(value = "/provideUser/{id}")
    public User provideUser(@PathVariable Long id) {
        return new User();
    }

    @RequestMapping(value = "/provideUsers/{ids}")
    public List<User> provideUsers(@PathVariable String ids) {
        System.out.println("ids=" + ids);
        List<User> list = new ArrayList<>();
        list.add(new User());
        list.add(new User());
        list.add(new User());
        return list;
    }
}
