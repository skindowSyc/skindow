package com.skindow.groovy;

import groovy.lang.GroovyClassLoader;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @ Author     ：syc.
 * @ Date       ：Created in 15:58 2020/9/10
 * @ Description：
 * @ Modified By：
 * @ Version:
 */
public class GroovyTest {
    public static void main(String[] args) {
        GroovyClassLoader groovyClassLoader = new GroovyClassLoader();
        ConcurrentHashMap<Object, Object> objectObjectConcurrentHashMap = new ConcurrentHashMap<>();
        objectObjectConcurrentHashMap.put(1,2);
    }
}
