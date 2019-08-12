package com.skindow.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.skindow.service.DemoService;

/**
 * Created by Administrator on 2019/8/7.
 */
@Service
public class DemoServiceImpl implements DemoService {
    public String sayHello(String name) {
        return "hey,my name is " + name;
    }

    public String sayHello(String name, Integer age) {
        return "hey,my name is " + name + " I'm "+ age +" years old";
    }
}