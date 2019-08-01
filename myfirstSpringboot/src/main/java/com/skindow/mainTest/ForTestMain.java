package com.skindow.mainTest;

import com.skindow.forInterface.*;
import com.skindow.proxy.MyCglibProxyFactory;
import com.skindow.proxy.MyJDKProxyFactory;

/**
 * Created by Administrator on 2019/7/30.
 */
public class ForTestMain {
    public static void main(String[] args)
    {
        Integer size = 10000000;
        // 目标对象
//        For for_1 = new ForImpl_forTest_1(size);
//        // 给目标对象创建代理对象
//        for_1 =(For) new MyJDKProxyFactory(for_1).getProxy();
//        //通过代理对象执行目标对象的方法
//        for_1.doSomething();
//        // 目标对象
//        For for_2 = new ForImpl_forTest_2(size);
//        // 给目标对象创建代理对象
//        for_1 =(For) new MyJDKProxyFactory(for_2).getProxy();
//        //通过代理对象执行目标对象的方法
//        for_1.doSomething();
//        ForImpl_forTest_1 forTest1 = new ForImpl_forTest_1(size);
//        forTest1 = (ForImpl_forTest_1)new MyCglibProxyFactory(forTest1).getProxyInstance();
//        forTest1.doSomething();

        ForImpl_forTest_4 forTest4 = new ForImpl_forTest_4(size);
        forTest4 = (ForImpl_forTest_4)new MyCglibProxyFactory(forTest4).getProxyInstance();
        forTest4.doSomething();

//        ForImpl_forTest_3 forTest3 = new ForImpl_forTest_3(size);
//        forTest3 = (ForImpl_forTest_3)new MyCglibProxyFactory(forTest3).getProxyInstance();
//        forTest3.doSomething();

        ForImpl_forTest_5 forTest5 = new ForImpl_forTest_5(size);
        forTest5 = (ForImpl_forTest_5)new MyCglibProxyFactory(forTest5).getProxyInstance();
        forTest5.doSomething();
    }
}
