package com.skindow.algorithm.numberOfDaffodils;

/**
 * @ Description   :打印出所有的 "水仙花数 "，所谓 "水仙花数 "是指一个三位数，其各位数字立方和等于该数本身。例如：153是一个
 * "水仙花数 "，因为153=1的三次方＋5的三次方＋3的三次方
 * @ Author        :  skindow
 * @ CreateDate    :  2020/1/13$ 15:14$
 */
public class NumberOfDaffodils {
    public static void main(String[] args) {
        int a, b, c;
        for (int i = 101; i < 1000; i++) {
            //个位数
            a = i % 10;
            //百位数
            b = i / 10 % 10;
            //千位数
            c = i / 100;
            if (a * a * a + b * b * b + c * c * c == i) {
                System.out.println(String.format("a = %s ,b = %s ,c = %s", a, b, c));
                System.out.println(i);
            }
        }
    }
}
