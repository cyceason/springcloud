package com.cyc.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * zuul的核心，过滤器
 * Created by cyc_e on 2017/9/2.
 */
@Component
public class AccessFilter extends ZuulFilter {
    /**
     * 过滤类型，在请求的哪个生命周期执行
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
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
     * 过滤器逻辑
     *
     * @return
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println("cookie name : " + cookie.getName() + "cookie value : " + cookie.getValue());
            }
        }
        RequestContext.getCurrentContext().addZuulRequestHeader("cychead", "cycheadvalue");
//        System.out.println("url : " + request.getRequestURL().toString());
//        String accessToken = request.getParameter("accessToken");
//        if (accessToken == null) {
//            ctx.setSendZuulResponse(false);// 不对其进行路由
//            ctx.setResponseStatusCode(401);
//            ctx.setResponseBody("body content");
//            return null;
//        }
//        System.out.println("校验通过");
        return null;
    }
}
