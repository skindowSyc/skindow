package com.skindow.designPtterns.decoratedWithPattern;

/**
 * Created by skindow on 2019/11/1.
 */
public class Yellow extends Color {
    private Color color;
    public Yellow(Color color) {
        this.color = color;
    }
    public String addColor()
    {
        return this.color.addColor() + "黄色";
    }
}
