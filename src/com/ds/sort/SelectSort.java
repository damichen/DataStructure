package com.ds.sort;

import java.util.Arrays;

/**
 * 选择排序 --先确定最小值
 */
public class SelectSort {
    public static void main(String[] args) {
//        int[] arr = {-2,101, 34, 119, 1, -1, 90, 123};
//        selectSort(arr);
//        System.out.println(Arrays.toString(arr));
        int[] arr = getArr();
        long start = System.currentTimeMillis();
        System.out.println("排序中...");
        selectSort(arr);//这边耗时比冒泡小一些，主要是 ，减少了对数组的操作
        long end = System.currentTimeMillis();
        System.out.println("排序完成，共花费: " + (end - start) + "毫秒");
    }

    private static int[] getArr() {
        int len = 5 * 10000;
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 10000);
        }
        return arr;
    }
    //选择排序

    /**
     * 时间复杂度O(n^2) , 与冒泡区别就是 选择排序，先确定最小值 ， 冒泡确定最大值
     *
     * @param arr
     */
    public static void selectSort(int arr[]) {
        if (arr == null && arr.length == 0)
            return;
        int minIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                //这里记录最小的下标，等下替换 ，减少赋值操作
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            if (i != minIndex) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }
}
