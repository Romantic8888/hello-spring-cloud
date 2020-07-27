package com.funtl.hello.spring.cloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author ls
 * @Date 2020/07/27 17:55:13
 * @Description
 * @Version 1.0
 **/
@Component
public class LoginFilter extends ZuulFilter {
    /**
     * @Author ls
     * @Date 2020/07/27 17:57:41
     * @Description 配置过滤类 有四种不同生命周期的过滤器类型
     * 1、pre:路由之前
     * 2、routing:路由之时
     * 3、post:路由之后
     * 4、error:发送错误调用
     * @Param []
     * @Return java.lang.String
     **/
    @Override
    public String filterType() {
        return "pre";
    }
    /**
     * @Author ls
     * @Date 2020/07/27 17:59:39
     * @Description 配置过滤的顺序 值越小 优先级越高
     * @Param []
     * @Return int
     **/

    @Override
    public int filterOrder() {
        return 0;
    }
    /**
     * @Author ls
     * @Date 2020/07/27 18:00:10
     * @Description 配置是否需要过滤 true/需要，false/不需要
     * @Param []
     * @Return boolean
     **/

    @Override
    public boolean shouldFilter() {
        return true;
    }
    /**
     * @Author ls
     * @Date 2020/07/27 18:01:04
     * @Description 过滤器的具体业务代码
     * @Param []
     * @Return java.lang.Object
     **/

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String token = request.getParameter("token");
        if (null==token){
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(401);
            try {
                HttpServletResponse response = currentContext.getResponse();
                response.setContentType("text/html;charset=utf-8");
                currentContext.getResponse().getWriter().write("非法请求");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
