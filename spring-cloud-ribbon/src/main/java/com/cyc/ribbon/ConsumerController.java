package com.cyc.ribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by cyc_e on 2017/8/17.
 */
@RestController
public class ConsumerController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/ribbon-consumer", method = RequestMethod.GET)
    public String consumer() {

        // get请求方式1， ｛1｝：占位符； 第二个参数 ： 返回类型， 第三个参数 ： 填充占位符
        // restTemplate.getForEntity("http://SPRING-CLOUD-PROVIDER/provideHello?name={1}", String.class, "对应占位符{1}").getBody();
        // get请求方式2,
        // restTemplate.getForObject("http://SPRING-CLOUD-PROVIDER/provideHello?name={1}", String.class);

        /*
            post三种请求方式
            post请求一 : 参数一：url
            参数二 ： 可以是普通对象，会将请求对象转换为一个HttpEntity对象来处理，会被视作完整的body来处理
                      也可以是HttpEntity对象，会被当作一个完成的HTTP请求处理，不仅包含body，也包含header
          */
//        restTemplate.postForEntity(null, null, null);
//        restTemplate.postForObject(null, null, null);
//        restTemplate.postForLocation(null, null);

        // put 三种请求方式， 跟post一样
//        restTemplate.put(null, null);

        // delete　
//        restTemplate.delete(null);


        // 地址为服务名称，不是ip地址或域名
        return restTemplate.getForEntity("http://SPRING-CLOUD-PROVIDER/provideHello", String.class).getBody();


    }
}
