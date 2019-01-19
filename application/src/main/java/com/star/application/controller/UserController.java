package com.star.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class UserController {

    @RequestMapping(value = {"/content"}, method = RequestMethod.GET)
    public String content() {
        return "content";
    }

    @RequestMapping(value = {"/model"}, method = RequestMethod.GET)
    public String model() {
        return "model";
    }

}
