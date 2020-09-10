package com.skindow.jexl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;
import org.apache.ibatis.jdbc.Null;
import org.springframework.util.StringUtils;

/**
 * @ Author     ：syc.
 * @ Date       ：Created in 11:33 2020/9/10
 * @ Description：
 * @ Modified By：
 * @ Version:
 */
@Slf4j
public class JexlTest {
    public static void main(String[] args) {
        JexlEngine engine = new JexlEngine();
        String testStr = "测试";
        JexlContext context = new MapContext();
        String jexlTest = "StringUtils.isEmpty(value)";
        context.set("value",testStr);
        context.set("StringUtils",StringUtils.class);
        boolean evaluate = (boolean) engine.createExpression(jexlTest).evaluate(context);
        log.info("\n 字符 【{}】是否为空 :{} ",testStr,evaluate ? "是":"不是");


        AbstractPerson xm = new AbstractPerson() {
            @Override
            public void stopWork() {
                log.info("\n{} 已经准备在下班了", this.getName());
            }

            @Override
            public void working() {
                log.info("\n{} 正在开会", this.getName());
            }
        };
        context = new MapContext();
        String personWorkJexl = "AbstractPerson.work(date)";
        context.set("AbstractPerson",xm);
        context.set("date", null);
        String personAgeIsAdult = "AbstractPerson.age >= 18";
        xm.setAge(19);
        xm.setGender(AbstractPerson.Gender.BOY);
        xm.setName("小明");
        engine.createExpression(personWorkJexl).evaluate(context);
        boolean isAdult = (boolean)engine.createExpression(personAgeIsAdult).evaluate(context);
        log.info("\n {} 是否成年 : {}",xm.getName(),isAdult ? "是": "还没");

    }
}
