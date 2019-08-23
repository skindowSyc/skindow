package com.skindow.mainTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/8/21.
 */
public class TestArrayList {
    public static void main(String[] args)
    {
        List<String> objects = new ArrayList<String>();
        List<String> objects1 = new ArrayList<String>();
        objects.add("11");
        objects.get(0);
        objects.remove("");
        objects.add(0,"");
        Object[] o = new Object[5];
        System.out.println(o.length);
        o[4] = null;
        System.out.println(o.length);
        objects.addAll(objects1);
    }
}
