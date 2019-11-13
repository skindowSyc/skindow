package com.skindow.designPtterns.genericDemo;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * @ Description   :
 * @ Author        :  skindow
 * @ CreateDate    :  2019/11/11$ 13:59$
 */
public abstract class Father<T> {
    private Class objectClass;

    private Class keyClass;

    private Class valueClass;

    private ParamTypeEnum paramTypeEnum;
    
    @SuppressWarnings("unchecked")
    public Father()
    {
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type type = genericSuperclass.getActualTypeArguments()[0];
        if (ParameterizedType.class.isAssignableFrom(type.getClass()))
        {
            ParameterizedType parameterizedType = (ParameterizedType)type;
            Class rawType = (Class<T>) parameterizedType.getRawType();
            if (Map.class.isAssignableFrom(rawType.getClass()))
            {
                keyClass = (Class) parameterizedType.getActualTypeArguments()[0];
                valueClass =(Class) parameterizedType.getActualTypeArguments()[1];
                paramTypeEnum = ParamTypeEnum.MAP;
            }
            else
            {
                valueClass =(Class) parameterizedType.getActualTypeArguments()[0];
                paramTypeEnum = ParamTypeEnum.COLLECTION;
            }
        }
        else
        {
            objectClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
            paramTypeEnum = ParamTypeEnum.SIMPLE;
        }
    }
    public void receiveAndExecuteMsg(String message)
    {
        Object object = null;
        if (paramTypeEnum == ParamTypeEnum.SIMPLE)
        {
            object = objectClass;
        }
    }
}
