package com.skindow.controller;

import com.skindow.service.DemoService;
import com.skindow.service.pojo.Book;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;


/**
 * Created by Administrator on 2019/8/7.
 */
@Controller
@Slf4j
@RequestMapping("/api/demo")
@Api("skindow-test")
public class DemoController {
    @Autowired
    private DemoService demoService;

    @RequestMapping("/test")
    @ResponseBody
    @ApiIgnore//使用该注解忽略这个API
    public String test()
    {
        return demoService.sayHello("skindow");
    }

    @RequestMapping("/testAop")
    @ResponseBody
    @ApiOperation(httpMethod = "GET", value = "测试AOP", consumes = "application/json")
    public String testAop(@RequestParam(value="name") String name,@RequestParam(value="age") Integer age)
    {
        return demoService.sayHello(name,age);
    }

    @RequestMapping("/setRedis")
    @ResponseBody
    @ApiOperation(httpMethod = "GET", value = "给redis新建key-value", consumes = "application/json")
    public String setRedis(@RequestParam(value="value") String value,@RequestParam(value="key") String key)
    {
        return demoService.testRedis(key,value);
    }

    @RequestMapping("/getRedis")
    @ResponseBody
    @ApiOperation(httpMethod = "GET", value = "在redis通过key获取value", consumes = "application/json")
    public String getRedis(@RequestParam(value="key") String key)
    {
        return demoService.getValueByKey(key);
    }

    @RequestMapping(value="/setBook",method= RequestMethod.POST)
    @ResponseBody
    @ApiOperation(httpMethod = "POST", value = "在redis中持久化对象Book", consumes = "application/json")
    public String Set(@RequestParam(value="key") String key,@RequestBody Book book)
    {
        return  demoService.setBook(key,book);
    }
}
