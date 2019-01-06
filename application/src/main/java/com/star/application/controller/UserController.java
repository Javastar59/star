package com.star.application.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.star.common.api.UserApi;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class UserController {

    @Reference(url = "dubbo://127.0.0.1:20881", version = "1.0.0")
    private UserApi userApi;

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello() {
        return userApi.hello();
    }
}
