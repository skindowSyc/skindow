package com.skindow.designPtterns.decoratedWithPattern;

/**
 * Created by skindow on 2019/11/1.
 */
public class main {
    public static void main(String[] args)
    {
        Color color = new Red(new Black(new Yellow(new Color("白色"))));
        System.out.print("color" + color.addColor());
    }
}
