package com.skindow.designPtterns.adapterMode;

/**
 * Created by skindow on 2019/11/1.
 */
public class main {
    public static void main(String[] args)
    {
        Integer i = 500;
        System.out.println("---------------未使用适配器---------------");
        Apple apple = new Apple(i);
        apple.Charging();

        System.out.println("---------------使用错误的适配器---------------");
        Apple apple2 = new Apple(i);
        Adapter adapter = new Adapter_110(apple2);
        apple2.setAdapter(adapter);
        apple2.Charging();

        System.out.println("---------------使用错正确的适配器---------------");
        Apple apple1 = new Apple(i);
        Adapter adapter1 = new Adapter_220(apple1);
        apple1.setAdapter(adapter1);
        apple1.Charging();

    }
}
