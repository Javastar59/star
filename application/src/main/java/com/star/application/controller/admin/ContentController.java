package com.star.application.controller.admin;


import com.alibaba.dubbo.config.annotation.Reference;
import com.star.common.api.ContentApi;
import com.star.common.domain.admin.Content;
import com.star.common.domain.response.ResponseVo;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/admin/content")
public class ContentController {

    @Reference(url = "dubbo://127.0.0.1:20881", version = "1.0.0", lazy = true)
    private ContentApi contentApi;

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ResponseVo save(@RequestBody Content content) {
        return ResponseVo.success(contentApi.save(content));
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

    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public ResponseVo findAll() {
        return ResponseVo.success(contentApi.findAll());
    }

}
