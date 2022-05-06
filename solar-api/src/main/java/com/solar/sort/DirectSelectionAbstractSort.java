package com.solar.sort;

/**
 * 直接选择排序
 * @author hushaoge
 * @date 2021/8/26
 */
public class DirectSelectionAbstractSort extends AbstractSort {
    public static void main(String[] args) {
        int[] elements = RandomArrayGenerator.getRandomArray(10000, 100000 );
        print(elements);
        long s = System.nanoTime();
        print(new DirectSelectionAbstractSort().sort(elements));
        long e = System.nanoTime();
        System.out.println(e - s);
    }

    @Override
    public int[] sort(int[] elements) {
        int len = elements.length;
        for (int i = 0; i < len ; i++) {
            int minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if(elements[minIndex] > elements[j]) {
                    minIndex = j;
                }
            }
            if(minIndex == i) {
                continue;
            }
            // 交换
            int tmp = elements[minIndex];
            elements[minIndex] = elements[i];
            elements[i] = tmp;
        }

        return elements;
    }

    public int[] sort2(int[] elements) {
        int len = elements.length;
        for (int i = 0; i < len ; i++) {
            int minIndex = i;
            int j = i + 1;
            while (j < len) {
                if(elements[minIndex] > elements[j]) {
                    minIndex = j;
                }
                j++;
            }

            if(minIndex == i) {
                continue;
            }
            // 交换
            int tmp = elements[minIndex];
            elements[minIndex] = elements[i];
            elements[i] = tmp;
        }

        return elements;
    }
}
