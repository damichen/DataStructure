package com.ds.sort;

import java.util.Arrays;

//冒泡排序
public class BubbleSort {
    public static void main(String[] args) {
//        int arr[] = {3, 9, -1, 10, 20};
//
//        System.out.println("排序前");
//        System.out.println(Arrays.toString(arr));
//        bubbleSort(arr);
//        System.out.println("排序后");
//        System.out.println(Arrays.toString(arr));
        int[] arr = getArr();
        long start = System.currentTimeMillis();
        System.out.println("排序中...");
        bubbleSort2(arr);
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

    /**
     * @param arr 需要排序的数组
     *            <p>
     *            这里时间复杂度为 O(n^2)
     *            优化：在某次排序没有变动一个位置，表示排序完成，直接退出
     */
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length == 0)
            return;
        int temp = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void bubbleSort2(int[] arr) {
        if (arr == null || arr.length == 0)
            return;
        int temp = 0;
        boolean flag = true;//这里先假定一个标志，如果有变动就改变，一次循环没有变动就代表完成了
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = false;
                }
            }
            if (flag) {
                break;//退出循环，排序完成
            } else {
                flag = true;
            }
        }
    }
}
