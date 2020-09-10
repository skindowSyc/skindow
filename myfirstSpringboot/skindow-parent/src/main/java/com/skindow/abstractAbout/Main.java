package com.skindow.abstractAbout;

/**
 * @ Author     ：syc.
 * @ Date       ：Created in 15:42 2020/5/14
 * @ Description：
 * @ Modified By：
 * @ Version:
 */
public class Main {
    public static void main(String[] args) {
        //多态 向上转型(自动转型)
        Person person = new XiaoHong();
        person.doSonething();
        System.out.println("===========");
        //向下转型
        doMian(new XiaoMing());
        System.out.println("===========");
        doMian(new XiaoHong());
        //如果直接将父类强行向下转型
        XiaoMing x = (XiaoMing) new Father();
        x.play();
    }
    private static void doMian(Father father){
        if (father instanceof XiaoHong){
            XiaoHong x = (XiaoHong)father;
            x.play();
        }
        if (father instanceof XiaoMing){
            XiaoMing x = (XiaoMing) father;
            x.play();
        }
    }
}
