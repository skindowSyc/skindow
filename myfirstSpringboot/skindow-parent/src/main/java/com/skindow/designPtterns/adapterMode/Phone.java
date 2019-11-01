package com.skindow.designPtterns.adapterMode;

/**
 * Created by skindow on 2019/11/1.
 */
public abstract class Phone {
    private Integer i;
    public Phone(Integer i)
    {
        this.i = i;
    }
    public Integer getV()
    {
        return i;
    }
    public void setV(Integer i)
    {
        this.i = i;
    }
    private Adapter adapter;
    public void setAdapter(Adapter adapter)
    {
        this.adapter = adapter;
    }
    public Adapter getAdapeter()
    {
        return adapter;
    }
    public void Charging(){}
}
