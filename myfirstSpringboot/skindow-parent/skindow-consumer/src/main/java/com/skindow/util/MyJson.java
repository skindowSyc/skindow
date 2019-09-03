package com.skindow.util;

import com.alibaba.fastjson.JSON;

/**
 * Created by Administrator on 2019/9/3.
 */
public class MyJson {
    public static String toJson(Object src) {
        String json = JSON.toJSONString(src);
        return json;
    }
}
