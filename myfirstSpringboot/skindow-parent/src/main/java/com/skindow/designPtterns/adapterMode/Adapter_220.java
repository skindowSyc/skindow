package com.skindow.designPtterns.adapterMode;

/**
 * Created by skindow on 2019/11/1.
 */
public class Adapter_220 implements Adapter{
    private Phone phone;
    public Adapter_220(Phone phone)
    {
        this.phone = phone;
    }
    public void charge()
    {
        System.out.print("适配前为" + phone.getV() + "V电压");
        phone.setV(220);
    }
}
