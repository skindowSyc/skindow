package com.skindow.designPtterns.decoratedWithPattern;

/**
 * Created by skindow on 2019/11/1.
 */
public class Color {
    private String color;

    public Color(String color){
        this.color = color;
    }

    public Color() {
    }

    public String addColor()
    {
        return color;
    }
}
