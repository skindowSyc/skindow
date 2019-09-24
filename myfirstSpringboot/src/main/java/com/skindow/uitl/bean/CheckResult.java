package com.skindow.uitl.bean;

import lombok.Data;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by skindow on 2019/9/20.
 */
@Data
public class CheckResult {
    private static final DecimalFormat DF = new DecimalFormat("0.00");;
    public CheckResult(Object oldDta, Object newData, String name) {
        if (oldDta instanceof BigDecimal)
        {
            BigDecimal bo = (BigDecimal) oldDta;
            if (bo != null)
            {
                this.oldDta = DF.format(oldDta);
            }
        }
        else
        {
            this.oldDta = oldDta;
        }
        if (newData instanceof BigDecimal)
        {
            BigDecimal bn = (BigDecimal)newData;
            if (bn != null)
            {
                this.newData = DF.format(bn);
            }
        }
        else
        {
            this.newData = newData;
        }
        this.name = name;
    }
    /**
     * 原值
     */
    private Object oldDta;
    /**
     * 新值
     */
    private Object newData;
    /**
     * 名称
     */
    private String name;
}
