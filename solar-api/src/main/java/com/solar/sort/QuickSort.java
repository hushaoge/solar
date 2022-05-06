package com.solar.sort;

/**
 * 快速排序
 *
 * 分治  递归
 * @author hushaoge
 * @date 2021/8/26
 */
public class QuickSort extends AbstractSort {

    public static void main(String[] args) {
        int[] elements = RandomArrayGenerator.getRandomArray(10000, 100000 );
//        int[] elements = {1, 2, 3, 4};
//        int[] elements = {497, 161, 258};
        print(elements);
        long s = System.nanoTime();
        print(new QuickSort().sort(elements));
        long e = System.nanoTime();
        System.out.println(e - s);
    }

    /**
     * 排序
     *
     * @param elements
     * @return
     */
    @Override
    public int[] sort(int[] elements) {
        return quickSort2(elements, 0, elements.length - 1);
    }

    private int[] quickSort(int[] elements, int start, int end) {
        int benchmark = start;
        int compare = end;
        while (benchmark != compare){
            if(elements[benchmark] > elements[compare] ) {
                if(compare > benchmark) {
                    swap(elements, benchmark, compare);
                    int t = compare;
                    compare = benchmark + 1;
                    benchmark = t;
                } else {
                    compare++;
                }
            } else {
                if(compare > benchmark) {
                    compare--;
                } else {
                    swap(elements, benchmark, compare);
                    int t = compare;
                    compare = benchmark - 1;
                    benchmark = t;
                }
            }
        }
        if (benchmark - start > 1) {
            quickSort(elements, start, benchmark - 1);
        }
        if(end - benchmark > 1 ) {
            quickSort(elements, benchmark + 1, end);
        }
        return elements;
    }

    private int[] quickSort2(int[] elements, int start, int end) {
        if(start >= end) {
            return elements;
        }
        // 获取基准
        int benchmark = getBenchmark(elements, start, end);
        quickSort2(elements, start, benchmark - 1);
        quickSort2(elements, benchmark + 1, end);
        return elements;
    }

    private int getBenchmark(int[] elements, int start, int end) {
        int left = start;
        int right = end;
        int benchmarkValue = elements[start];
        while (left < right) {

            while (left < right && benchmarkValue < elements[right]) {
                right --;
            }
            if(left < right) {
                swap(elements, left, right);
                left ++;
            }

            while (left < right && elements[left] < benchmarkValue) {
                left ++;
            }
            if(left < right) {
                swap(elements, left, right);
                right--;
            }

        }
        return left;
    }


    public int[] quickSort3(int[] arr,int begin,int end)
    {
        //如果区间不只一个数
        if(begin < end)  {
            // 将区间的第一个数作为基准数
            int temp = arr[begin];
            // 从左到右进行查找时的“指针”，指示当前左位置
            int i = begin;
            // 从右到左进行查找时的“指针”，指示当前右位置
            int j = end;
            //不重复遍历
            while(i < j) {
                //当右边的数大于基准数时，略过，继续向左查找
                //不满足条件时跳出循环，此时的j对应的元素是小于基准元素的
                while(i<j && arr[j] > temp) {
                    j--;
                }
                //将右边小于等于基准元素的数填入右边相应位置
                arr[i] = arr[j];
                //当左边的数小于等于基准数时，略过，继续向右查找
                //(重复的基准元素集合到左区间)
                //不满足条件时跳出循环，此时的i对应的元素是大于等于基准元素的
                while(i<j && arr[i] <= temp) {
                    i++;
                }
                //将左边大于基准元素的数填入左边相应位置
                arr[j] = arr[i];
            }
            //将基准元素填入相应位置
            arr[i] = temp;
            //此时的i即为基准元素的位置
            //对基准元素的左边子区间进行相似的快速排序
            quickSort3(arr,begin,i-1);
            //对基准元素的右边子区间进行相似的快速排序
            quickSort3(arr,i+1,end);
            return arr;
        } else {
            //如果区间只有一个数，则返回
            return arr;
        }
    }

    private void swap (int[] elements, int i, int j) {
        int tmp = elements[i];
        elements[i] = elements[j];
        elements[j] = tmp;
    }

}
