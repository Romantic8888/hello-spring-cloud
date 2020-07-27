package com.funtl.hello.spring.cloud.zuul.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author ls
 * @Date 2020/07/27 17:21:23
 * @Description 路由 hello-spring-cloud-web-admin-feign 失败时的回调
 * @Version 1.0
 **/
@Component
public class WebAdminFeignFallbackProvider implements FallbackProvider {

    @Override
    public String getRoute() {
        // serviceId 如果需要所有调用都支持回调 则return "*" 或者 return null
        return "hello-spring-cloud-web-admin-feign";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {
            /**
             * @Author ls
             * @Date 2020/07/27 17:27:48
             * @Description 网关向api服务请求失败了，但是消费者客户端向网关发起的请求是成功的
             * 不应该把api的404 500等问题抛给客户端
             * 网关和api服务集群对于客户端来说是黑盒
             * @Param []
             * @Return org.springframework.http.HttpStatus
             **/

            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.OK.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.OK.getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                ObjectMapper objectMapper=new ObjectMapper();
                Map<String,Object> map=new HashMap<>();
                map.put("status",200);
                map.put("message","无法连接,请检查您的网络");
                return new ByteArrayInputStream(objectMapper.writeValueAsString(map).getBytes("UTF-8"));
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers=new HttpHeaders();
                //和 getBody中的内容编码一致
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
                return null;
            }
        };
    }
}
