package com.solar.sort;

/**
 * 希尔排序
 * @author hushaoge
 * @date 2021/8/27
 */
public class ShellSort extends AbstractSort{

    public static void main(String[] args) {
        int[] elements = RandomArrayGenerator.getRandomArray(19 );
        long s = System.nanoTime();
        print(new ShellSort().sort(elements));
        long e = System.nanoTime();
        System.out.println("耗时(ms):"+ (e - s)/1000/1000);
    }

    /**
     * 排序
     *
     * @param elements
     * @return
     */
    @Override
    public int[] sort(int[] elements) {
        int len = elements.length;
        int gap = len/2;

        while(gap > 0) {
            // 内部是快速插入的算法，只是增量是gap
            for (int i = 0; i < len; i += gap) {
                int j = i;
                int current = elements[i];
                while (j > 0 && current < elements[j-gap]) {
                    //交换
                    elements[j] = elements[j-gap];
                    j -= gap;
                }
                // 找到位置后再把当前要插入的值插入
                elements[j] = current;
            }
            gap /= 2;
        }
        return elements;
    }

}
