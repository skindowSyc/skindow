package com.skindow.abstractAbout;

/**
 * @ Author     ：syc.
 * @ Date       ：Created in 15:40 2020/5/14
 * @ Description：
 * @ Modified By：
 * @ Version:
 */
public class XiaoHong extends Father {
    @Override
    void moving() {
        super.moving();
    }

    @Override
    void speaking() {
        super.speaking();
    }

    @Override
    void learning() {
        super.learning();
        System.out.println("小红学习钢琴");
    }
    void play(){
        System.out.println("小红跳绳子");
    }
}
