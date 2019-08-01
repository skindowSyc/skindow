package com.skindow.forInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/7/30.
 */
public class ForImpl_forTest_5 implements For{
    public Integer getSize() {
        return size;
    }
    public void setSize(Integer size) {
        this.size = size;
    }
    private Integer size;
    public ForImpl_forTest_5(){}
    public ForImpl_forTest_5(Integer size)
    {
        this.size = size;
    }
    @Override
    public void doSomething() {
        List<Integer> list = new ArrayList<>();
        Integer t = null;
        System.out.println("循环外部创建引用对象 ==》t = new Integer(i);");
        for (Integer i = 0; i < size; i++)
        {
            t = new Integer(i);
            list.add(t);
        }
    }
}
