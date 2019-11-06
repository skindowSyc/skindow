package com.skindow.reflection;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ Description   :
 * @ Author        :  shaoYongchang
 * @ CreateDate    :  2019/11/6$ 10:27$
 */
@Data
@SuppressWarnings("serial")
@Slf4j
@Service
public class ReflectionTest extends Exception implements Cloneable,Serializable,Comparable {

    private static final long serialVersionUID = -5696669667647624450L;

    private String string;

    private Integer integer;

    private BigDecimal bigDecimal;

    private Date date;

    protected Long along;

    Double aDouble;

    public float aFloat;

    public Float getaFloat() {
        return aFloat;
    }

    public void setaFloat(Float aFloat) {
        this.aFloat = aFloat;
    }

    public Long getAlong() {
        return along;
    }

    public void setAlong(Long along) {
        this.along = along;
    }

    public Double getaDouble() {
        return aDouble;
    }

    public void setaDouble(Double aDouble) {
        this.aDouble = aDouble;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    public BigDecimal getBigDecimal() {
        return bigDecimal;
    }

    public void setBigDecimal(BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public ReflectionTest()
    {

    }

    public ReflectionTest(String string, Integer integer, BigDecimal bigDecimal, Date date) {
        this.string = string;
        this.integer = integer;
        this.bigDecimal = bigDecimal;
        this.date = date;
    }

    public ReflectionTest(String message, String string, Integer integer, BigDecimal bigDecimal, Date date) {
        super(message);
        this.string = string;
        this.integer = integer;
        this.bigDecimal = bigDecimal;
        this.date = date;
    }

    public ReflectionTest(String message, Throwable cause, String string, Integer integer, BigDecimal bigDecimal, Date date) {
        super(message, cause);
        this.string = string;
        this.integer = integer;
        this.bigDecimal = bigDecimal;
        this.date = date;
    }

    public ReflectionTest(Throwable cause, String string, Integer integer, BigDecimal bigDecimal, Date date) {
        super(cause);
        this.string = string;
        this.integer = integer;
        this.bigDecimal = bigDecimal;
        this.date = date;
    }

    public ReflectionTest(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String string, Integer integer, BigDecimal bigDecimal, Date date) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.string = string;
        this.integer = integer;
        this.bigDecimal = bigDecimal;
        this.date = date;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public Object ObjectA(){
        class classA{};
        return new classA();
    }

    private String printName (String name,Integer age)
    {
        return new StringBuilder().append(name).append(Integer.toString(age)).toString();
    }

    private <T> T privateMethod(T t)
    {
        return t;
    }
    protected void protectedMethod()
    {

    }
    void defaultMehtod()
    {

    }
}
