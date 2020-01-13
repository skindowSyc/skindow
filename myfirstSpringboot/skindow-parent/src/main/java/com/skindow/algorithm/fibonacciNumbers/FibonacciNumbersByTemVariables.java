package com.skindow.algorithm.fibonacciNumbers;

/**
 /**题目：古典问题：有一对兔子，从出生后第3个月起每个月都生一对兔子，小兔子长到第三个月后每个月又生一对兔子，
 * 假如兔子都不死，问每个月的兔子总数为多少？
 * f(n) = f(n-1) + f(n-3)
 * 0，0，1，  1，1，2，3，4，6，9，13，19，28，41，60
 * @ Author        :  skindow
 * @ CreateDate    :  2020/1/13$ 14:14$
 */
public class FibonacciNumbersByTemVariables {
    //从上面规律可以看出 计算n个数据 只需 n-1 到 n-3 得数据，就是3个数据，所以我们需要定义1，2月份的数据之外，还需要定义0月份得数据为0
    public static void main(String[] args){
        //定义一月份 和 二月份得数据
        int y_1 = 1,y_2 = 1,y_0 = 1;
        int mouth = 12;
        for(int i = 3; i <= mouth ; i++){
            //这里假设当前得月份为3
            int result = y_0 + y_2;
            System.out.println(String.format("=====第%d月兔子总数为%d对====",i,result));
            //这里为下一个迭代做准备
            int f = y_2;
            y_2 = result;
            y_0 = y_1;
            y_1 = f;
        }
    }
}
