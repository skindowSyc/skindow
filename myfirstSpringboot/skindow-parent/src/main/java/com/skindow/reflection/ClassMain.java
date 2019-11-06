package com.skindow.reflection;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;


/**
 * @ Description   :
 * @ Author        :  shaoYongchang
 * @ CreateDate    :  2019/11/6$ 10:32$
 */
public class ClassMain {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        org.slf4j.Logger logger = LoggerFactory.getLogger(ClassMain.class);

        //通过路径获取运行类
        Class<?> aClass1 = Class.forName("com.skindow.reflection.ReflectionTest");
        //通过指定class获取运行类
        Class<ReflectionTest> reflectionTestClass = ReflectionTest.class;
        //通过实例来获取运行类
        ReflectionTest reflectionTest = new ReflectionTest();
        reflectionTest.setString("xiaohong");
        Class<? extends ReflectionTest> aClass = reflectionTest.getClass();

        //注意第二个返回的对象 ，他的引用对象没有泛型? ,?代表任何对象的实例，也就是说在第二个类不是实例

        logger.info("reflectionTestClass.getClassLoader() ; {}",reflectionTestClass.getClassLoader()); //获取类的加载器

        logger.info("reflectionTestClass.getSigners() ; {}",aClass.getSigners());//此方法返回这个类的签名，或者为null，如果没有签名，如果这个对象表示一个基本类型或void，则返回null

        logger.info("reflectionTestClass.getSigners() ; {}",reflectionTestClass.getSuperclass()); //获取它的父类

        Annotation[] annotations = aClass1.getDeclaredAnnotations();
        logger.info("reflectionTestClass.getAnnotations(); {}", Arrays.stream(annotations).map(a -> a.toString()).collect(Collectors.joining(","))); //获取该类的注解类
        //注意只能获取到运行时的注解类

        logger.info("reflectionTestClass.getInterfaces(); {}", Arrays.stream(reflectionTestClass.getInterfaces()).map(a -> a.toString()).collect(Collectors.joining(","))); //获取该类的接口数组

        ReflectionTest reflectionTest1 = aClass.newInstance(); //通过无参构造函数构建
        logger.info("reflectionTest1; {}", reflectionTest1.toString());

        logger.info("reflectionTestClass.getConstructors(); {}", Arrays.stream(reflectionTestClass.getConstructors()).map(a -> a.toString()).collect(Collectors.joining(","))); //获取构造函数的数组
        Constructor<?>[] constructors = reflectionTestClass.getConstructors();//获取构造函数的数组
        Constructor<?> constructor = constructors[0];
        //通过构造函数实例化对象
        Constructor<ReflectionTest> constructor1 = reflectionTestClass.getConstructor(String.class, Integer.class, BigDecimal.class, Date.class);
        ReflectionTest reflectionTest2 = constructor1.newInstance("1", new Integer(2), new BigDecimal(2), new Date());
        logger.info("reflectionTest2; {}", reflectionTest2.toString());
        logger.info("reflectionTest2.getDate(); {}",reflectionTest2.getDate());

    }
}
