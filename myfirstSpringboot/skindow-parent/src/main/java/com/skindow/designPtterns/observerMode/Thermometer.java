package com.skindow.designPtterns.observerMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by skindow on 2019/11/1.
 */
public class Thermometer {
    private List<Behavior> list = new ArrayList<>();
    public Thermometer()
    {
    }
    public void addPerson(Behavior behavior)
    {
        list.add(behavior);
    }

    public void notifyAllPerson(Integer i)
    {
        for (Behavior behavior : list) {
            behavior.temperature(i);
        }
    }
}
