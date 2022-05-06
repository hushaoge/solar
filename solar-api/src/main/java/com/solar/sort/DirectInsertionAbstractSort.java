package com.solar.sort;

/**
 * 直接插入排序
 * @author hushaoge
 * @date 2021/8/25
 */
public class DirectInsertionAbstractSort extends AbstractSort {

    public static void main(String[] args) {
        int[] elements = RandomArrayGenerator.getRandomArray(10 );
        long s = System.nanoTime();
        print(new DirectInsertionAbstractSort().sort3(elements));
        long e = System.nanoTime();
        System.out.println("耗时(ms):"+ (e - s)/1000/1000);
    }

    @Override
    public int[] sort (int[] elements) {
        int len = elements.length;

        for (int i = 1; i < len ; i++) {
            for (int j = i; j > 0 ; j--) {
                // 如果
                if (elements[j] < elements[j-1]) {
                    //交换
                    int tmp = elements[j];
                    elements[j] = elements[j-1];
                    elements[j-1] = tmp;
                } else {
                    break;
                }
            }
        }
        return elements;
    }

    public int[] sort2 (int[] elements) {
        int len = elements.length;
        for (int i = 1; i < len ; i++) {
            int j = i;
            while (j > 0 && elements[j] < elements[j-1]) {
                //交换
                int tmp = elements[j];
                elements[j] = elements[j-1];
                elements[j-1] = tmp;
                j--;
            }
        }
        return elements;
    }

    public int[] sort3 (int[] elements) {
        int len = elements.length;
        for (int i = 1; i < len ; i++) {
            int j = i;
            int current = elements[i];
            while (j > 0 && current < elements[j-1]) {
                //交换
                elements[j] = elements[j-1];
                j--;
            }
            // 找到位置后再把当前要插入的值插入
            elements[j] = current;
        }
        return elements;
    }
}
