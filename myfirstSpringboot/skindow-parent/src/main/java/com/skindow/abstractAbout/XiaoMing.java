package com.skindow.abstractAbout;

/**
 * @ Author     ：syc.
 * @ Date       ：Created in 15:38 2020/5/14
 * @ Description：
 * @ Modified By：
 * @ Version:
 */
public class XiaoMing extends Father {
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
        System.out.println("小明学习奥数");
    }
    void play(){
        System.out.println("玩游戏");
    }
}
