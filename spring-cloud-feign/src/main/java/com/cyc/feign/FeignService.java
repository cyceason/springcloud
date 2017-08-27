package com.cyc.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 定义一个接口， @FeignClient("spring-cloud-provider") 为服务的名称
 * Created by cyc_e on 2017/8/20.
 */
@FeignClient("SPRING-CLOUD-PROVIDER")
public interface FeignService {

    /**
     * 为服务中的某个对外提供的方法
     *
     * @return 在Feign中 @PathVariable, @RequestBody @RequestParam @RequestHeader等等value的值绝对不能少
     */
    @RequestMapping(value = "/provideUser/{id}")
    String provideHello(@PathVariable(value = "id") Long id);
}
