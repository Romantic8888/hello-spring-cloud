package com.funtl.hello.spring.cloud.web.admin.feign.service;

import com.funtl.hello.spring.cloud.web.admin.feign.service.hystrix.AdminServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author ls
 * @Date 2020/07/20 10:28:33
 * @Description
 * @Version 1.0
 **/
@FeignClient(value = "hello-spring-cloud-service-admin",fallback = AdminServiceHystrix.class)
public interface AdminService {
    @RequestMapping(value = "hi",method = RequestMethod.GET)
    String sayHi(@RequestParam(value = "message")String message);
}
