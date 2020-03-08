package com.ds.sort;

import java.util.Arrays;

//希尔排序 --对插入排序进行优化
public class ShellSort {
    public static void main(String[] args) {
//        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0, -2, 31, 100, -10};
//        shellSort(arr);
//        System.out.println(Arrays.toString(arr));
        // 创建要给 80000 个的随机的数组
        int[] arr = getArr();
        long start = System.currentTimeMillis();
        System.out.println("排序中...");
        shellSort(arr);//这边耗时比冒泡小一些，主要是 ，减少了对数组的操作
        long end = System.currentTimeMillis();
        System.out.println("排序完成，共花费: " + (end - start) + "毫秒");
    }
    private static int[] getArr() {
        int len = 500 * 10000;
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 10000);
        }
        return arr;
    }
    //希尔排序 -- 分组进行插入排序 arr.length/(2^n) -O(nlogn)  线性对数阶
    public static void shellSort(int[] arr) {
        int gap = arr.length / 2;
        for (int i = gap; i > 0; i /= 2) {
            //这里是插入排序 ，但是里边有i组数据 ,j =i 保证前面就是组数的值
            for (int j = i; j < arr.length; j++) {
                //移动法
                int insertIndex = j - i;
                int insertValue = arr[j];
                while (insertIndex >= 0 && arr[insertIndex] > insertValue) {
                    arr[insertIndex + i] = arr[insertIndex];
                    insertIndex -= i;
                }
                arr[insertIndex + i] = insertValue;
            }
        }
    }

}
