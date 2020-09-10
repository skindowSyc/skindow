package com.skindow.abstractAbout;

/**
 * @ Author     ：syc.
 * @ Date       ：Created in 17:52 2020/5/15
 * @ Description：
 * @ Modified By：
 * @ Version:
 */
public class Test {
    public static void main(String[] args) {
        String[] a = new String[2];
        //向上转型
        Object[] b = a;
        a[0] = "hi";
        //向下转型integer interof
        if (b[1] instanceof String) {
            b[1] = "skindow";
        } else if (b[1] instanceof Integer) {
            b[1] = 1;
        }
    }
}
