package com.skindow.algorithm.fibonacciNumbers;

import com.alibaba.fastjson.JSON;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @ Description   :题目：古典问题：有一对兔子，从出生后第3个月起每个月都生一对兔子，小兔子长到第三个月后每个月又生一对兔子，
 * 假如兔子都不死，问每个月的兔子总数为多少？
 * @ Author        :  skindow
 * @ CreateDate    :  2020/1/13$ 10:11$
 */
public class FibonacciSequenceSimulation {
    //第三个月开始生小兔子
    private static int MOUTH_START_BORN = 3;
    //定义一只兔子生几只兔子
    private static int DOUBLE_RABBIT = 1;
    //开始为一对兔子
    private static int STRAT_RABBIT_NUMBER = 2;
    //截止到第几月
    private static int SPECIFY_MOUTH = 12;
    //记录成熟得兔子
    private static int MATURERABBIT = 0;

    public static void main (String[] args){
        int total = STRAT_RABBIT_NUMBER;
        //为每只兔子记录出生天数，一旦达到3个月成熟期则清楚该KEY
        Map<Integer,Integer> rabbiMap = new HashMap<>();
        for (int i = 1; i <= STRAT_RABBIT_NUMBER; i++) {
            rabbiMap.put(i,1);
        }
        for(int mouth = 1;mouth <= SPECIFY_MOUTH;mouth ++){
            if(mouth > 1){
                //从第二月开始加一
                rabbiMap = growJanuary(rabbiMap);
            }
            if (MATURERABBIT > 0){
                //计算方式为 成熟得兔子翻倍(新生兔子)
                total = total + MATURERABBIT * DOUBLE_RABBIT ;
                System.out.println(String.format("第%d月成熟兔子为%d只，这个月新诞生得兔子数量%d只",mouth,MATURERABBIT,MATURERABBIT * DOUBLE_RABBIT));
                //新诞生得兔子记录出生月份 为避免key冲突，就以当前总数为key得起始进行定义Key
                if (MATURERABBIT * DOUBLE_RABBIT > 0){
                    for (int i = total; i < total + (MATURERABBIT * DOUBLE_RABBIT); i ++){
                        rabbiMap.put(i,0);
                    }
                }
//                System.out.println("rabbiMap toJSON" + JSON.toJSONString(rabbiMap));
            }
            System.out.println(String.format("=====第%d月兔子总数为%d只====",mouth,total));
        }
    }

    //记录的每只兔子月份加一，一旦成熟（3个月就删除该key）
    private static Map<Integer,Integer> growJanuary(Map<Integer,Integer> rabbiMap){
        //为避免remove操作导致无限循环，这里新增有一个临时Map
        Map<Integer,Integer> newMap = new HashMap<>(rabbiMap.size());
        for (Map.Entry<Integer, Integer> integerIntegerEntry : rabbiMap.entrySet()) {
            Integer value = integerIntegerEntry.getValue();
            if ( value+ 1 == MOUTH_START_BORN){
                //成熟兔子队列加1
                MATURERABBIT += 1;
            }else{
                integerIntegerEntry.setValue(value + 1);
                newMap.put(integerIntegerEntry.getKey(),integerIntegerEntry.getValue());
            }
        }
        return newMap;
    }
}
