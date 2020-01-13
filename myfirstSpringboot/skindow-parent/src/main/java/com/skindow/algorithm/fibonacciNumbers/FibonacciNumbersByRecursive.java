package com.skindow.algorithm.fibonacciNumbers;

/**题目：古典问题：有一对兔子，从出生后第3个月起每个月都生一对兔子，小兔子长到第三个月后每个月又生一对兔子，
 * 假如兔子都不死，问每个月的兔子总数为多少？
 * f(n) = f(n-1) + f(n-3)
 * 0，0，1，  1，1，2，3，4，6，9，13，19，28，41，60
 * @ Description   :
 * @ Author        :  skindow
 * @ CreateDate    :  2020/1/13$ 13:47$
 */
public class FibonacciNumbersByRecursive {
    public static void main(String[] args){
        int mouth = 6;
        System.out.println(String.format("=====第%d月兔子总数为%d对====",mouth,rabbitNum(mouth)));
    }

    //这里用采用递归方式进行计算
    private static int rabbitNum(int n){
        //这里定义1月份 和 2月份得数据为1
        if (n == 1|| n == 2){
            return 1;
        }else{
            //由于3月份 第0月份没有数据所以也直接返回
            if (n == 3){
                return 2;
            }else{
                return rabbitNum(n -1 ) + rabbitNum(n - 3);
            }
        }
    }
}
