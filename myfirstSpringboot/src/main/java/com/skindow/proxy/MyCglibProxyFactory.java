package com.skindow.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2019/7/30.
 */
public class MyCglibProxyFactory implements MethodInterceptor {
    private Object target;
    public MyCglibProxyFactory (Object target)
    {
        this.target = target;
    }

    //给目标对象创建一个代理对象
    public Object getProxyInstance(){
        //1.工具类
        Enhancer en = new Enhancer();
        //2.设置父类
        en.setSuperclass(target.getClass());
        //3.设置回调函数
        en.setCallback(this);
        //4.创建子类(代理对象)
        return en.create();

    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("开始记录时间");
        long begin = System.currentTimeMillis();
        //执行目标对象的方法
        Object returnValue = method.invoke(target, objects);
        long end = System.currentTimeMillis();
        System.out.println(target.getClass().getName() + " 总耗时：" + (end - begin ) + "ms");
        return returnValue;
    }
}
