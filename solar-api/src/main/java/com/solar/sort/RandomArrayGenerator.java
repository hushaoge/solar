package com.solar.sort;

import java.util.Random;

/**
 * @author hushaoge
 * @date 2021/8/26
 */
public class RandomArrayGenerator {
    public static int[] getRandomArray(int n) {
        int bound = Math.max(n, 100);
        return getRandomArray(n, bound);
    }
    /**
     * 获取指定长度的随机数组
     */
    public static int[] getRandomArray(int n, int bound) {
        int[] array = new int[n];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(bound);
        }
        return array;
    }
}
