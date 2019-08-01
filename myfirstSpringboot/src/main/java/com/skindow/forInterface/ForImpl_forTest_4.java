package com.skindow.forInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/7/30.
 */
public class ForImpl_forTest_4 implements For{
    public Integer getSize() {
        return size;
    }
    public void setSize(Integer size) {
        this.size = size;
    }
    private Integer size;
    public ForImpl_forTest_4(){}
    public ForImpl_forTest_4(Integer size)
    {
        this.size = size;
    }
    @Override
    public void doSomething() {
        System.out.println("for循环内部循环创建引用对象 -》 Integer t = new Integer(i);");
        List<Integer> list = new ArrayList<>();
        for (Integer i = 0; i < size; i++)
        {
            Integer t = new Integer(i);
            list.add(t);
        }
    }
}
