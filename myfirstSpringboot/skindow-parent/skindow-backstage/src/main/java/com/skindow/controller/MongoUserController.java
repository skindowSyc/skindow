package com.skindow.controller;

import com.skindow.MongoDBService.MongoUserService;
import com.skindow.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2019/8/30.
 */
@Controller
@Slf4j
@RequestMapping("/mongo/user")
@Api("mongo-user")
public class MongoUserController {
    @Autowired
    private MongoUserService mongoUserService;

    @RequestMapping(value = "/insertUser",method = RequestMethod.POST)
    @ApiOperation(httpMethod = "POST", value = "添加用户", consumes = "application/json")
    @ResponseBody
    public String insertUser(@RequestBody User user, @RequestParam(value="collectionName",required = true) String collectionName)
    {
        if (null == user)
        {
            return "user is null!";
        }
        try {
            mongoUserService.saveUser(user,collectionName);
        } catch (Exception e) {
            log.error("mongoUserService.saveUser error {}",e);
            return "error";
        }
        return "success";
    }
    @RequestMapping(value = "/findUserByName",method = RequestMethod.POST)
    @ApiOperation(httpMethod = "POST", value = "查找用户", consumes = "application/json")
    @ResponseBody
    public User findUserByName(@RequestParam("name") String name,@RequestParam(value="collectionName",required = true) String collectionName)
    {
        User user = null;
        try {
            user =  mongoUserService.findUserByName(name,collectionName);
        } catch (Exception e) {
            log.error("mongoUserService.findUserByName error {}",e);
            return null;
        }
        return user;
    }
    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    @ApiOperation(httpMethod = "POST", value = "修改用户", consumes = "application/json")
    @ResponseBody
    public String updateUser(@RequestBody User user, @RequestParam(value="collectionName",required = true) String collectionName)
    {
        try {
            mongoUserService.updateUser(user,collectionName);
        } catch (Exception e) {
            log.error("mongoUserService.updateUser error {}",e);
            return "error";
        }
        return "success";
    }
    @RequestMapping(value = "/deleteTestById",method = RequestMethod.POST)
    @ApiOperation(httpMethod = "POST", value = "删除用户", consumes = "application/json")
    @ResponseBody
    public String deleteTestById(@RequestParam("id")Integer id,@RequestParam(value="collectionName",required = true) String collectionName){
        try {
            mongoUserService.deleteTestById(id,collectionName);
        } catch (Exception e) {
            log.error("mongoUserService.deleteTestById error {}",e);
            return "error";
        }
        return "success";
    }
}
