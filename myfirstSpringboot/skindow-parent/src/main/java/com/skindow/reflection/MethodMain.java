package com.skindow.reflection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @ Description   :
 * @ Author        :  skindow
 * @ CreateDate    :  2019/11/6$ 13:35$
 */
public class MethodMain {
    public static void main(String[] args)
    {
        Logger logger = LoggerFactory.getLogger(MethodMain.class);
        Class<?> aClass1 = null;
        try {
            aClass1 = Class.forName("com.skindow.reflection.ReflectionTest");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Method[] declaredMethods = aClass1.getDeclaredMethods();//获取所有方法包含private
        logger.info("aClass1.getDeclaredMethods(); {}", Arrays.stream(declaredMethods).map(a -> a.toString()).collect(Collectors.joining(",")));

        Method[] methods = aClass1.getMethods();//获取所有本类的public方法以及父类的方法
        logger.info("aClass1.getMethods(); {}", Arrays.stream(methods).map(a -> a.toString()).collect(Collectors.joining(",")));

        ReflectionTest reflectionTest = null;
        try {
            reflectionTest = (ReflectionTest)aClass1.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Class<?> aClass = reflectionTest.ObjectA().getClass();

        Method enclosingMethod = aClass.getEnclosingMethod();//如果一个class表示在方法中的一个本地或匿名class, 那么通过java.lang.Class.getEnclosingMethod()方法将返回的底层类的立即封闭方法。 反之则为NULL
        logger.info("aClass1.getEnclosingMethod(); {}", enclosingMethod);

        Method printName = null;
        try
        {
            printName = aClass1.getMethod("printName", String.class, Integer.class);//通过名称以及入参类型指定一个方法 如果该方法修饰为provider时则无法获取到该方法
        }
        catch (NoSuchMethodException e)
        {
            logger.info("aClass1.getMethod 获取Private修饰的方法失败!");
            try
            {
                printName = aClass1.getDeclaredMethod("printName", String.class, Integer.class);
            } catch (NoSuchMethodException e1) {
                e1.printStackTrace();
            }
            logger.info("aClass1.getDeclaredMethod : {}",printName.toGenericString());//toGenericString()输出整个方法的字符串,例如private java.lang.String com.skindow.reflection.ReflectionTest.printName(java.lang.String,java.lang.Integer)
        }

        try {
            Object xx = printName.invoke(reflectionTest, "xx", 1);
            logger.info("printName.invoke {}",xx);
        } catch (IllegalAccessException e) {
            logger.info("调用private失败");
            printName.setAccessible(true);//设置私有方法的权限,允许调用
            try {
                Object xx = printName.invoke(reflectionTest, "xx", 1);
                logger.info("printName.invoke {}",xx);
            } catch (IllegalAccessException e1)
            {
            } catch (InvocationTargetException e1)
            {
            }
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        String name = printName.getName();
        logger.info("printName.getName() : {}",name);//获取方法名称

        Class<?>[] parameterTypes = printName.getParameterTypes();//获取方法入参的实例类型列表
        logger.info("printName.getParameterTypes(); {}", Arrays.stream(parameterTypes).map(a -> a.toString()).collect(Collectors.joining(",")));

        Method privateMethod= null;
        try {
            privateMethod = aClass1.getDeclaredMethod("privateMethod",Object.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        TypeVariable<Method>[] typeParameters = privateMethod.getTypeParameters();//该方法返回一个代表该泛型声明中声明的类型变量TypeVariable对象的数组。
        logger.info("privateMethod.getTypeParameters(); {}", Arrays.stream(typeParameters).map(a -> a.toString()).collect(Collectors.joining(",")));
        logger.info("privateMethod.getParameterTypes(); {}", Arrays.stream(privateMethod.getParameterTypes()).map(a -> a.toString()).collect(Collectors.joining(",")));
        logger.info("privateMethod.getGenericParameterTypes(); {}", Arrays.stream(privateMethod.getGenericParameterTypes()).map(a -> a.toString()).collect(Collectors.joining(",")));

        Type[] genericParameterTypes = printName.getGenericParameterTypes();//方法返回一个Type对象的数组，它以声明顺序表示此Method对象表示的方法的形式参数类型  注意如果时泛型的话它会返回泛型否则的话则返回形参,你可以把它理解为getTypeParameters 和 getParameterTypes 的组合体
        logger.info("printName.getGenericParameterTypes(); {}", Arrays.stream(genericParameterTypes).map(a -> a.toString()).collect(Collectors.joining(",")));


    }
}
