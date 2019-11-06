package com.skindow.reflection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @ Description   :
 * @ Author        :  shaoYongchang
 * @ CreateDate    :  2019/11/6$ 11:11$
 */
public class FileMain {
    public static void main (String[] args) throws Exception
    {
        Logger logger = LoggerFactory.getLogger(FileMain.class);
        Class<?> aClass1 = Class.forName("com.skindow.reflection.ReflectionTest");

        Field[] fields = aClass1.getFields();//获取该类的公开属性 只能获取到public属性
        logger.info("aClass1.getFields(); {}", Arrays.stream(fields).map(a -> a.toString()).collect(Collectors.joining(",")));

        Field[] declaredFields = aClass1.getDeclaredFields();//获取该类的所有属性 包含私有属性 比如provider
        logger.info("aClass1.getDeclaredFields(); {}", Arrays.stream(declaredFields).map(a -> a.toString()).collect(Collectors.joining(",")));

        Field aFloat = aClass1.getField("aFloat");//获取 通过查找public指定名称的属性
        logger.info("aClass1.getField : {}",aFloat);

        Field date = aClass1.getDeclaredField("date");//获取通过查找所有指定名称的属性
        logger.info("aClass1.getDeclaredField : {}",date);

        String name = aFloat.getName();//获取属性名称
        logger.info("aClass1.getName : {}",name);

        Object o1 = aClass1.newInstance();
        Object o = aFloat.get(o1);//获取指定类型的实例 注意参数为实例对象,也就是反射对象的实例,根据实例获取对象属性的值
        logger.info("aFloat.get : {}",o);

        Class<?> type = aFloat.getType();//获取属性的实例类型
        logger.info("aFloat.getType: {}",type);

        aFloat.set(o1,new Float(1));//设置实例的值
        Object f = aFloat.get(o1);
        logger.info("f: {}",f);

        float aFloat1 = aFloat.getFloat(o1);//获取指定类型的值,注意这里返回的类型不是Object,你想拿到某个类型就直接指定哪个类型的方法
        //注意: 这里如果aFloat 属性不是基本类型则会报错 会报以下错误,说将float封装类转换为float类型出错,所以如果要使用这个方法时,对应的属性一定要是基本类型不是封装类
//        Exception in thread "main" java.lang.IllegalArgumentException: Attempt to get java.lang.Float field "com.skindow.reflection.ReflectionTest.aFloat" with illegal data type conversion to float
//        at sun.reflect.UnsafeFieldAccessorImpl.newGetIllegalArgumentException(UnsafeFieldAccessorImpl.java:69)
//        at sun.reflect.UnsafeFieldAccessorImpl.newGetFloatIllegalArgumentException(UnsafeFieldAccessorImpl.java:140)
//        at sun.reflect.UnsafeObjectFieldAccessorImpl.getFloat(UnsafeObjectFieldAccessorImpl.java:65)
//        at java.lang.reflect.Field.getFloat(Field.java:648)
//        at com.skindow.reflection.FileMain.main(FileMain.java:47)
        logger.info("aFloat.getFloat: {}", aFloat1);

        aFloat.setFloat(o1,2f);//只当类型进行赋值
        Object f1 = aFloat.get(o1);
        logger.info("aFloat.getFloat: {}",f1);

        int modifiers = aFloat.getModifiers();//Field的getModifiers()方法返回int类型值表示该字段的修饰符。 其中，该修饰符是java.lang.reflect.Modifier的静态属性。
        logger.info("aFloat.getModifiers() : {}",modifiers);
//        对应表如下：
//        PUBLIC: 1
//        PRIVATE: 2
//        PROTECTED: 4
//        STATIC: 8
//        FINAL: 16
//        SYNCHRONIZED: 32
//        VOLATILE: 64
//        TRANSIENT: 128
//        NATIVE: 256
//        INTERFACE: 512
//        ABSTRACT: 1024
//        STRICT: 2048
    }
}
