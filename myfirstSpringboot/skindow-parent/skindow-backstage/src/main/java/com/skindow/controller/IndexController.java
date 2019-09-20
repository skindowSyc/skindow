package com.skindow.controller;

import com.skindow.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2019/9/3.
 */
@Controller
@Slf4j
@RequestMapping("/welcome/demo")
@Api("skindow-test")
public class IndexController {
    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    @ApiOperation(httpMethod = "GET", value = "通过ID查询用户", consumes = "application/json")
    public String queryUserById(Model model, @RequestParam(value = "id") String id)
    {
        model.addAttribute("user",userService.getUser(id));
        return "index";
    }
}
