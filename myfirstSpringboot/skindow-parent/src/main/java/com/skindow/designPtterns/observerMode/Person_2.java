package com.skindow.designPtterns.observerMode;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by skindow on 2019/11/1.
 */
@Slf4j
public class Person_2 implements Behavior {
    @Override
    public void temperature(Integer i) {
        if (i == 100)
        {
            System.out.println("【" + this.getClass().getName() + "】通过观察温度计发现水已经烧开了！");
        }
        else
        {
            System.out.println("【" + this.getClass().getName() + "】通过观察温度计此时温度计为 "+ i + "度！");
        }
    }
}
