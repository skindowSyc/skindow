package com.skindow.controller;

import com.alibaba.fastjson.JSON;
import com.skindow.pojo.User;
import com.skindow.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2019/8/20.
 */
@Controller
@Slf4j
@RequestMapping("/api/user")
@Api("skindow-user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/insertUser",method = RequestMethod.POST)
    @ApiOperation(httpMethod = "POST", value = "添加用户", consumes = "application/json")
    @ResponseBody
    public String insertUser(@RequestBody User user)
    {
        if (null == user)
        {
            return "user is null!";
        }
        return userService.setUser(user);
    }

    @RequestMapping("/queryUserById")
    @ApiOperation(httpMethod = "GET", value = "通过ID查询用户", consumes = "application/json")
    @ResponseBody
    public String queryUserById(@RequestParam(value = "id") String id)
    {
        return JSON.toJSONString(userService.getUser(id));
    }
}
