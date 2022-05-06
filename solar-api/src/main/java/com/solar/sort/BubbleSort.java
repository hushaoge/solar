package com.solar.sort;

/**
 * 冒泡排序
 * @author hushaoge
 * @date 2021/8/26
 */
public class BubbleSort extends AbstractSort {
    public static void main(String[] args) {
        int[] elements = RandomArrayGenerator.getRandomArray(100000, 1000000 );
        long s = System.nanoTime();
        print(new BubbleSort().sort2(elements));
        long e = System.nanoTime();
        System.out.println(e - s);
    }

    @Override
    public int[] sort (int[] elements) {
        int len = elements.length;

        for (int i = 0; i < len; i++) {
            for (int j = len - 1; j > i; j--) {
                if(elements[j] < elements[j-1]) {
                    int tmp = elements[j-1];
                    elements[j-1] = elements[j];
                    elements[j] = tmp;
                }
            }
        }

        return elements;
    }

    /**
     * 用标志记录操作，如果本身有一定的顺序了，速度会比上面的方法快
     * @param elements
     * @return
     */
    public int[] sort2 (int[] elements) {
        int len = elements.length;
        for (int i = 0; i < len; i++) {
            int sort = 1;
            for (int j = len - 1; j > i; j--) {
                if(elements[j] < elements[j-1]) {
                    int tmp = elements[j-1];
                    elements[j-1] = elements[j];
                    elements[j] = tmp;
                    sort = 0;
                }
            }

            if(sort == 1) {
                return elements;
            }
        }

        return elements;
    }

}
