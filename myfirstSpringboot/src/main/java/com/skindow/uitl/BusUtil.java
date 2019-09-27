package com.skindow.uitl;

import com.skindow.annotion.AttributeComForObject;
import com.skindow.annotion.AttributeComparison;
import com.skindow.uitl.bean.CheckResult;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by skindow on 2019/9/24.
 */
public class BusUtil {
    /**比较个对象属性的值是否相等 配合注解AttributeComparison使用
     * @param o
     * @param n
     * @param results
     * @return
     * @throws Exception
     */
    public static List<CheckResult> attributeComparison(Object o, Object n,List<CheckResult> results) throws Exception
    {
        if (o == null || n == null)
        {
            return results;
        }
        Class<?> oClass = o.getClass();
        Class<?> nClass = n.getClass();
        if (!oClass.isInstance(n))
        {
            throw new Exception("object_old {" + oClass.getTypeName() + "} and object_new {" + nClass.getTypeName() + "}  Inconsistent types, please enter two identical object types");
        }
        Field[] ofields = oClass.getDeclaredFields();
        if (ofields == null || ofields.length == 0)
        {
            return results;
        }
        CheckResult checkResult = null;
        for (Field ofield : ofields)
        {
            ofield.setAccessible(true);
            if (hashAnnotionByclass(ofield,AttributeComForObject.class))
            {
                Object o1 = ofield.get(o);
                Field nfield = nClass.getDeclaredField(ofield.getName());
                nfield.setAccessible(true);
                Object n1 = nfield.get(n);
                attributeComparison(o1,n1,results);
            }
            else
            {
                fileCheck(o, n, nClass, results, ofield);
            }
        }
        return results;
    }

    /** 比对成员变量属性
     * @param o
     * @param n
     * @param nClass
     * @param results
     * @param ofield
     * @throws Exception
     */
    private static void fileCheck(Object o, Object n, Class<?> nClass, List<CheckResult> results, Field ofield) throws Exception {
        if (!hashAnnotionByclass(ofield,AttributeComparison.class) || !isCheck(ofield))
        {
            return;
        }
        Field nfield = nClass.getDeclaredField(ofield.getName());
        nfield.setAccessible(true);
        if (nfield == null)
        {
            throw new Exception("Inconsistent object properties one is " + ofield.getName() + " another one is " + nfield.getName());
        }
        compareValue(o, n, results, ofield, nfield);
    }

    /** 开始比对属性
     * @param o
     * @param n
     * @param results
     * @param ofield
     * @param nfield
     * @throws Exception
     */
    public static void compareValue(Object o, Object n, List<CheckResult> results, Field ofield, Field nfield) throws Exception {
        CheckResult checkResult;
        Object on = null;
        Object ol = null;
        on = ofield.get(n);
        ol = nfield.get(o);
        if (on == null && ol == null)
        {
            return;
        }
        if (hashOneNull(ol,on))
        {
            checkResult = new CheckResult(ol,on,getAnnoStr(ofield));
            results.add(checkResult);
            checkResult = null;
            return;
        }
        Class<?> kc = null;
        if ((kc =comparableClassFor(ol)) != null)
        {
            if (compareComparables(kc,ol,on) != 0)
            {
                checkResult = new CheckResult(ol,on,getAnnoStr(ofield));
                results.add(checkResult);
                checkResult = null;
            }
            return;
        }
        //默认使用equals比较相同
        if (ol.toString().getClass().equals(on.toString()))
        {
            return;
        }
        else
        {
            getAnnoStr(ofield);
            checkResult = new CheckResult(ol,on,getAnnoStr(ofield));
            results.add(checkResult);
            checkResult = null;
        }
    }

    public static int compareComparables(Class<?> kc, Object k, Object x)
    {
        return (x == null || x.getClass() != kc ? 0 :
                ((Comparable)k).compareTo(x));
    }

    public static Class<?> comparableClassFor(Object x)
    {
        if (x instanceof Comparable) {
            Class<?> c; Type[] ts, as; Type t; ParameterizedType p;
            if ((c = x.getClass()) == String.class) // bypass checks
                return c;
            if ((ts = c.getGenericInterfaces()) != null)
            {
                for (int i = 0; i < ts.length; ++i)
                {
                    if (((t = ts[i]) instanceof ParameterizedType) &&
                            ((p = (ParameterizedType)t).getRawType() ==
                                    Comparable.class) &&
                            (as = p.getActualTypeArguments()) != null &&
                            as.length == 1 && as[0] == c) // type arg is c
                        return c;
                }
            }
        }
        return null;
    }
    /** 获取比对注解的name值
     * @param field
     * @return
     * @throws Exception
     */
    public static String getAnnoStr(Field field) throws Exception {
        AttributeComparison attributeComparison = (AttributeComparison)getAttributeComparison(field,AttributeComparison.class);
        if (attributeComparison == null)
        {
            throw new Exception("The object has no concern about the solution");
        }
        return attributeComparison.name();
    }

    /** 其中一个对象含有值则默认两者不同
     * @param o1
     * @param o2
     * @return
     */
    public static Boolean hashOneNull (Object o1,Object o2)
    {
        if (o1 != null && o2 == null)
        {
            return true;
        }
        if (o1 == null && o2 != null)
        {
            return true;
        }
        return false;
    }

    /** 是否满足比对条件
     * @param ofield
     * @return
     */
    public static Boolean isCheck (Field ofield)
    {
        Class<?> type = ofield.getType();
        if (type.isInterface())
        {
            return false;
        }
        if (hashAnnotionByclass(ofield, AttributeComForObject.class))
        {
            return false;
        }
        return true;
    }

    /** 获取属性比较注解
     * @param field
     * @return
     */
    public static Annotation getAttributeComparison(Field field,Class c)
    {
        Annotation[] annotations = field.getAnnotations();
        if (annotations == null || annotations.length == 0)
        {
            return null;
        }
        for (Annotation annotation : annotations)
        {
            if (annotation.annotationType().getTypeName().equals(c.getName()))
            {
                return annotation;
            }
        }
        return null;
    }

    /** 判断是否存在比对注解
     * @param ofield
     * @return
     */
    private static Boolean hashAnnotionByclass(Field ofield,Class c)
    {
        Annotation attributeComparison = getAttributeComparison(ofield, c);
        if (attributeComparison == null)
        {
            return false;
        }
        return true;
    }
}
