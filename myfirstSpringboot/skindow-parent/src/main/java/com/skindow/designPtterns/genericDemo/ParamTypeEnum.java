package com.skindow.designPtterns.genericDemo;

/**
 * @ Description   :
 * @ Author        :  skindow
 * @ CreateDate    :  2019/11/11$ 14:19$
 */
public enum ParamTypeEnum {
    MAP("map","map集合"),
    COLLECTION("collection","set list集合"),
    SIMPLE("simple","普通对象");
    private String name;
    private String desc;
    ParamTypeEnum(String name,String desc){
        this.name = name;
        this.desc = desc;
    }
}
