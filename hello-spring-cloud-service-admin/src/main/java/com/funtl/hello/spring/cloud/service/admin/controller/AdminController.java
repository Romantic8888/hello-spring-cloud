package com.funtl.hello.spring.cloud.service.admin.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ls
 * @Date 2020/07/07 22:31:57
 * @Description
 * @Version 1.0
 **/
@RestController
public class AdminController {
    @Value("${server.port}")
    private String port;
    @RequestMapping(value = "hi",method = RequestMethod.GET)
    public String sayHi(String message) {
        return String.format("This is message :%s port :%s",message,port);
    }

}
