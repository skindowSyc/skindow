package com.skindow.algorithm.leetcode;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author     ：syc.
 * @ Date       ：Created in 09:14 2020/4/23
 * @ Description：计算
 * @ Modified By：
 * @ Version:     1.0.0
 */
//硬币。给定数量不限的硬币，币值为25分、10分、5分和1分，编写代码计算n分有几种表示法。(结果可能会很大，你需要将结果模上1000000007)
//比如10
//10 = 5 + 5
//10 = 5 + 1 + 1 + 1 + 1 + 1
//10 = 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1

public class Coin {
    //按照顺序大小依次排序
    private static List<Integer> coinList = new ArrayList<Integer>(4){
        private static final long serialVersionUID = -8813097076851027285L;

        {
        add(1);
        add(5);
        add(10);
        add(20);
    }};
    //思路 先从大到小依次比较NUMBER 因为是从最大数开始的，如果最大数小于NUMBER这个时候，我们就把最大数当做NUMBER用于比较 一直嵌套循环下去，直到最小数为止
    //比如NUMBER 为26 这个是后和20 进行比较 20 小于26 这个时候，也就是 26 拆分为 20 和 6 这里就要计算20 的组合多大
    //第一步 将NUMBER 分解成n个20 和 NUMBER 其减去20*n 的组合
    private static final Integer NUMBER = 10;

    @SneakyThrows
    public static void main(String[] args) {
        Integer coinMax = coinList.get(coinList.size());
        Thread.sleep(2000);
        if (compareMaxToList(NUMBER) <= coinMax){

        }
        int coinMaxSize = NUMBER % coinMax;
        int Remaining = NUMBER - coinMaxSize * coinMax;
    }

    //比较集合里面的值，获取最大数
    private static Integer compareMaxToList(Integer i){
        if (i.compareTo(coinList.get(0)) == 0) {
            return coinList.get(0);
        }
        for (int j = coinList.size(); j >0 ; j--) {
            if (coinList.get(j).compareTo(i) < 0){
                return coinList.get(j);
            }
        }

        return coinList.get(0);
    }

}
