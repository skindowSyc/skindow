package com.skindow.algorithm.positiveIntegerFactorization;

/**
 * @ Description   :将一个正整数分解质因数。例如：输入90,打印出90=2*3*3*5
 * @ Author        :  skindow
 * @ CreateDate    :  2020/1/13$ 15:55$
 */
public class PositiveIntegerFactorization {
    public static void main(String[] args){
        int num = 223100;
        System.out.print(String.format("%d = ",num));
        for(int i = 2; i <= num ;i ++){
            while (i != num){
                if (num % i == 0){
                    num = num/i;
                    System.out.print(String.format("%d *",i));
                }else{
                    break;
                }
            }
            if (i == num){
                System.out.print(String.format("%d",i));
                System.out.println();
            }
        }
    }
}
