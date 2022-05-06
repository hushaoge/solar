package com.solar.sort;

/**
 * @author hushaoge
 * @date 2021/8/26
 */
public abstract class AbstractSort {
    public static void print(int[] elements) {
        for (int e : elements) {
            System.out.printf("%5d", e);
        }
        System.out.println();
        System.out.println("------------------------------------------");
    }

    /**
     * 排序
     * @param elements
     * @return
     */
    public abstract int[] sort(int[] elements);
}
