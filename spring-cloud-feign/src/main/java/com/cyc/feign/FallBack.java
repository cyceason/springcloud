package com.cyc.feign;

import org.springframework.stereotype.Component;

/**
 * 降级服务，实现 FeignService 接口中的各个方法
 * Created by cyc_e on 2017/8/27.
 */
@Component
public class FallBack implements FeignService {


    /**
     * 为服务中的某个对外提供的方法
     *
     * @param id
     * @return 在Feign中 @PathVariable, @RequestBody @RequestParam @RequestHeader等等注解中，value属性绝对不能少
     */
    @Override
    public String provideHello(Long id) {
        return "降级服务返回";
    }
}
