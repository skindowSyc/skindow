package com.skindow.designPtterns.decoratedWithPattern;

/**
 * Created by skindow on 2019/11/1.
 */
public class Black extends Color {
    private Color color;

    public Black(Color color)
    {
        this.color = color;
    }

    public String addColor()
    {
        return this.color.addColor() + "黑色";
    }
}
