package com.skindow.algorithm.calculationPrimeNumber;

/**
 * @ Description   :求n ~ m数之间素数得个数，并输出这些素数
 * @ Author        :  skindow
 * @ CreateDate    :  2020/1/13$ 14:49$
 */
public class CalculationPrimeNumber {
    private static final Integer START_NUM = 0;

    private static final Integer END_NUM = 200;

    private static Integer PRIME_NUM = 0;
    public static void main(String[] args){
        for (int i = START_NUM;i<END_NUM;i++){
            if (isPrime((double) i)){
                System.out.print(i + ",");
                PRIME_NUM ++;
                if (PRIME_NUM % 10 == 0){
                    System.out.println();
                }
            }
        }
        System.out.println();
        System.out.println(String.format("%d到%d之间共有素数%d个",START_NUM,END_NUM,PRIME_NUM));
    }
    //定义一个方法来计算该值是否为素数
    private static Boolean isPrime(Double num){
        double sqrt = Math.sqrt(num);
        for (int i = 2; i <= sqrt; i ++){
            if (num % i == 0){
                return false;
            }
        }
        return true;
    }
}
