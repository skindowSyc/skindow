package com.skindow.mainTest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/8/22.
 */
public class TestHashMap {
    private static  final int MAXIMUM_CAPACITY = 1 << 30;
    public static void main(String[] args)
    {
        Map<String,Object> m = new HashMap<String,Object>();
//        m.put("","");
//        int n = 1386861906;
//        n = n - 1;
//        n |= n >>> 1;
//        System.out.println(n); //7
//        n |= n >>> 2;
//        System.out.println(n);
//        n |= n >>> 4;
//        System.out.println(n);
//        n |= n >>> 8;
//        System.out.println(n);
//        n |= n >>> 16;
//        System.out.println(n);
//        n =  (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
//        System.out.println(n);
     String s = "Skindow";
     System.out.println("Skindow hashCode " + s.hashCode());
        System.out.println("Skindow hash" + hash(s));
    }
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
