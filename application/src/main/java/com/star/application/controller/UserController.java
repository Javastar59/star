package com.star.application.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.star.common.api.UserApi;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class UserController {

    @Reference(url = "dubbo://127.0.0.1:20881", version = "1.0.0", lazy = true)
    private UserApi userApi;

    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    public String goAdmin() {
        return "admin/home";
    }
}
