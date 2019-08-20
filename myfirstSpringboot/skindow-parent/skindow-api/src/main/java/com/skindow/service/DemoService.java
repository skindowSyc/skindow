package com.skindow.service;


import com.skindow.service.pojo.Book;

/**
 * Created by Administrator on 2019/8/7.
 */
public interface DemoService {
        String sayHello(String name);
        String sayHello(String name,Integer age);
        String testRedis(String key,String value);
        String getValueByKey(String key);
        String setBook(String key,Book book);
}
