package com.skindow.designPtterns.adapterMode;

/**
 * Created by skindow on 2019/11/1.
 */
public class Apple extends Phone {
    public Apple(Integer i) {
        super(i);
    }
    public void Charging()
    {
        if (super.getV() != 220 && super.getAdapeter() == null)
        {
            System.out.println("手机电压不符合该手机合规电压,由于没有适配器该手机即将烧毁");
        }
        if (super.getV() != 220 && super.getAdapeter() != null)
        {
            Adapter adapeter = super.getAdapeter();
            adapeter.charge();
            System.out.println("经过适配之后的电压为" + super.getV());
            if (super.getV() != 220)
            {
                System.out.println("该手机已经被烧毁");
            }
            else
            {
                System.out.println("该手机正常充电中。。。。");
            }
        }
    }
}
