package com.skindow.serviceImpl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.skindow.service.DemoService;
import com.skindow.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;


/**
 * Created by Administrator on 2019/8/7.
 */
@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    private RedisTemplate<Serializable, Object> defaultRedisTemplate;

    @Override
    public String sayHello(String name) {
        return "hey,my name is " + name;
    }

    @Override
    public String sayHello(String name, Integer age) {
        return "hey,my name is " + name + " I'm " + age + " years old";
    }

    @Override
    @Transactional
    public String testRedis(String key, String value) {
        String result = "success";
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            result = "key or value is empty!";
        } else {//10s有效期
            defaultRedisTemplate.opsForValue().set(key, value, 60, TimeUnit.SECONDS);
        }
        return result;
    }

    @Override
    public String getValueByKey(String key) {
        if (StringUtils.isEmpty(key)) {
            return "key is empty!";
        }
        return defaultRedisTemplate.opsForValue().get(key).toString();
    }

    @Override
    public String setBook(String key, Book book) {
        if (book == null) {
            return "book is null!";
        }
        defaultRedisTemplate.opsForValue().set(key, book);
        return "success";
    }
}