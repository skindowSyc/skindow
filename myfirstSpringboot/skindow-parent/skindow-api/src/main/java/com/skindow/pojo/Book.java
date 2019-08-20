package com.skindow.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/8/20.
 */
@Data
public class Book implements Serializable {
    private static final long serialVersionUID = -1324354924637626383L;
    /**
     * 书名
     */
    private String name;
    /**
     * 类别
     */
    private String type;
    /**
     * 价格
     */
    private String price;
}
