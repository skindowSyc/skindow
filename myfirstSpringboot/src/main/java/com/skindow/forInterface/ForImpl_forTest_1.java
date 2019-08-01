package com.skindow.forInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/7/30.
 */
public class ForImpl_forTest_1 implements For{
    public Integer getSize() {
        return size;
    }
    public void setSize(Integer size) {
        this.size = size;
    }
    private Integer size;
    public ForImpl_forTest_1(){}
    public ForImpl_forTest_1(Integer size)
    {
        this.size = size;
    }
    @Override
    public void doSomething() {
        List<Integer> list = new ArrayList<>();
        for (Integer i = 0; i < size; i++)
        {
            list.add(i);
        }
    }
}
