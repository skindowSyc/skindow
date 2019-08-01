package com.skindow.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2019/7/30.
 */
public class MyJDKProxyFactory {

    /**
     * 维护一个目标对象
     */
    private Object target;
    public MyJDKProxyFactory (Object target)
    {
        this.target = target;
    }

    /**Proxy.newProxyInstance注意该方法是在Proxy类中是静态方法,且接收的三个参数依次为:
     ClassLoader loader,:指定当前目标对象使用类加载器,获取加载器的方法是固定的
     Class<?>[] interfaces,:目标对象实现的接口的类型,使用泛型方式确认类型
     InvocationHandler h:事件处理,执行目标对象的方法时,会触发事件处理器的方法,会把当前执行目标对象的方法作为参数传入
     * @return 通过目标对象创建代理对象
     */
    public Object getProxy(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("开始记录时间");
                long begin = System.currentTimeMillis();
                Object invoke = method.invoke(target, args);
                long end = System.currentTimeMillis();
                System.out.println(target.getClass().getName() + " 总耗时：" + (end - begin ) + "ms");
                return invoke;
            }
        });
    }
}
