package com.skindow.abstractAbout;

/**
 * @ Author     ：syc.
 * @ Date       ：Created in 15:33 2020/5/14
 * @ Description：
 * @ Modified By：
 * @ Version:
 */
public abstract class Person {
    abstract void moving();
    abstract void speaking();
    abstract void learning();
    void doSonething(){
        learning();
        speaking();
        moving();
    }
}
