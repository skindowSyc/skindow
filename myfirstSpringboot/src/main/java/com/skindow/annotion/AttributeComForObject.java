package com.skindow.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 使用该注解得对象纳入对比范围
 * Created by skindow on 2019/9/20.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AttributeComForObject
{
}
