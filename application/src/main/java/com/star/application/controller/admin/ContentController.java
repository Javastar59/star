package com.star.application.controller.admin;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/content")
public class ContentController {

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save() {
        return "content";
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable("id") Long id) {
        return "content";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") Long id) {
        return "content";
    }

    @RequestMapping(value = "find/{id}", method = RequestMethod.GET)
    public String findOne(@PathVariable("id") Long id) {
        return "content";
    }

    @RequestMapping(value = "find", method = RequestMethod.GET)
    public String findAll() {
        return "content";
    }

    @RequestMapping(value = "page/{page}/{size}", method = RequestMethod.GET)
    public String page(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return "content";
    }
}
