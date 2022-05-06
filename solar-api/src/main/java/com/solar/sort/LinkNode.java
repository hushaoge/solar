package com.solar.sort;

/**
 * 链表
 * @author hushaoge
 * @date 2021/8/25
 */
public class LinkNode {
    public int value;
    public LinkNode next;

    public void print() {
        System.out.println(value);
    }

    public void printNext() {
        System.out.println(next.value);
    }
}
