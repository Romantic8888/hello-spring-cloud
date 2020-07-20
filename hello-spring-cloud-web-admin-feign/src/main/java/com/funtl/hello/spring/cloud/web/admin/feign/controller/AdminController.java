package com.funtl.hello.spring.cloud.web.admin.feign.controller;

import com.funtl.hello.spring.cloud.web.admin.feign.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ls
 * @Date 2020/07/20 10:33:06
 * @Description
 * @Version 1.0
 **/
@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;
    @RequestMapping(value = "hi",method = RequestMethod.GET)
    public String sayHi(String message){
        return adminService.sayHi(message);
    }
}
