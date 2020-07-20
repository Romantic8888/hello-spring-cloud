package com.funtl.hello.spring.cloud.web.admin.feign.service.hystrix;

import com.funtl.hello.spring.cloud.web.admin.feign.service.AdminService;
import org.springframework.stereotype.Component;

/**
 * @Author ls
 * @Date 2020/07/20 14:45:32
 * @Description
 * @Version 1.0
 **/
@Component
public class AdminServiceHystrix implements AdminService {

    @Override
    public String sayHi(String message) {
        return String.format("Hi you message is:%s but request bad",message);
    }
}
