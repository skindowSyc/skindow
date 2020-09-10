package com.skindow.abstractAbout;

/**
 * @ Author     ：syc.
 * @ Date       ：Created in 15:34 2020/5/14
 * @ Description：
 * @ Modified By：
 * @ Version:
 */
public class Father extends Person
{
    @Override
    void moving() {
        System.out.println("Father.moving");
    }

    @Override
    void speaking() {
        System.out.println("Father.speaking");
    }

    @Override
    void learning() {
        System.out.println("Father.learning");
    }
}
