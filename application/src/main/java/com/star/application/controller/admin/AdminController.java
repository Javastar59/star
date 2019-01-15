package com.star.application.controller.admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = {"/admin", "/home"}, method = RequestMethod.GET)
public class AdminController {

    @RequestMapping
    public String home() {
        return "admin/home";
    }

    @RequestMapping(value = "content")
    public String content() {
        return "admin/content/content";
    }
}
