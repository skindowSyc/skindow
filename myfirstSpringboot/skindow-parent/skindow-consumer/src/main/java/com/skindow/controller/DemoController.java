package com.skindow.controller;

import com.skindow.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2019/8/7.
 */
@Controller
@Slf4j
@RequestMapping("/api/demo")
public class DemoController {
    @Autowired
    private DemoService demoService;
    @RequestMapping("/rest")
    @ResponseBody
    public String test()
    {
        return demoService.sayHello("skindow");
    }
}
