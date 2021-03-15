package com.skindow.sql;

/**
 * @ Author     ：syc.
 * @ Date       ：Created in 13:57 2021/3/12
 * @ Description：
 * @ Modified By：
 * @ Version:
 */
public class GcTest {
    public static class Node {
        private Node pre;
    }

    public static void main(String[] args) {
        Node node = new Node();
        node.pre = node;
        System.gc();
        System.out.println(node);
    }
}
