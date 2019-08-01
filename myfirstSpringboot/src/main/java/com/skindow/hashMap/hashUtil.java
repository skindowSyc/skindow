package com.skindow.hashMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/7/30.
 */
public class hashUtil {
    static int hash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }
    public static void main(String[] args)
    {
//        int i = "hello".hashCode();
//        System.out.println("hello" + " hashCode : " + i + "  hash值为" + hash(i));
//        char c = 'A';
//        System.out.println("A=>" + new Integer(c));
        Map<String,Object> map = new HashMap<>();
        map.put("","");
    }
}
