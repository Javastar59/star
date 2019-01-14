package com.star.application.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.star.common.api.UserApi;
import org.springframework.stereotype.Controller;


@Controller
public class UserController {

    @Reference(url = "dubbo://127.0.0.1:20881", version = "1.0.0", lazy = true)
    private UserApi userApi;

}
