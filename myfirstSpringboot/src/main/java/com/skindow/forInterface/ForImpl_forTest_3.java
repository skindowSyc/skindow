package com.skindow.forInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/7/30.
 */
public class ForImpl_forTest_3 implements For{
    public Integer getSize() {
        return size;
    }
    public void setSize(Integer size) {
        this.size = size;
    }
    private Integer size;
    public ForImpl_forTest_3(){}
    public ForImpl_forTest_3(Integer size)
    {
        this.size = size;
    }
    @Override
    public void doSomething() {
        List<Integer> list = new ArrayList<>();
        Integer t = null;
        System.out.println("for循环外部创建引用对象，并且引用完 将其指向null ==》  t = new Integer(i); t = null;");
        for (Integer i = 0; i < size; i++)
        {
            t = new Integer(i);
            list.add(t);
            t = null;
        }
    }
}
