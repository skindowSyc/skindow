package com.skindow.forInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/7/30.
 */
public class ForImpl_forTest_2 implements For{
    private Integer size;
    public ForImpl_forTest_2(Integer size)
    {
        this.size = size;
    }
    @Override
    public void doSomething() {
        List<Integer> list = new ArrayList<>(size);
        for (Integer i = 0; i < size; i++)
        {
            list.add(i);
        }
    }
}
