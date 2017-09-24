package com.cyc.zuul;

import com.netflix.util.Pair;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * zuul的核心，过滤器
 * Created by cyc_e on 2017/9/2.
 */
@Component
public class PostFilter extends ZuulFilter {
    /**
     * 过滤类型，在请求的哪个生命周期执行
     *
     * @return
     */
    @Override
    public String filterType() {
        return "post";
    }

    /**
     * 多个过滤器时，过滤器的执行顺序
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 过滤器是否执行
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器逻辑， 校验能否拿到服务端返回的response头
     *
     * @return
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        List<Pair<String, String>> originResponseHeaders = ctx.getOriginResponseHeaders();
        List<Pair<String, String>> zuulResponseHeaders = RequestContext.getCurrentContext().getZuulResponseHeaders();
        if (originResponseHeaders != null) {
            for (Pair<String, String> pair : originResponseHeaders) {
                System.out.println("name : " + pair.first() + "value :" + pair.second());
            }
        }
        System.out.println("----------------------------------------------");
        if (originResponseHeaders != null) {
            for (Pair<String, String> pair : zuulResponseHeaders) {
                System.out.println("name : " + pair.first() + "value :" + pair.second());
            }
        }
        ctx.setResponseBody("errorbody");
        return null;
    }
}
