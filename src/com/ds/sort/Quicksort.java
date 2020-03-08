package com.ds.sort;

import java.util.Arrays;

/**
 * 快速排序--对冒泡排序的改进
 * 思想：
 * 通过一趟排序将要排序的数据分割成独立的两
 * 部分，其中一部分的所有数据都比另外一部分的所有数据都要小，然后再按此方法对这两部分数据分别进行快速排
 * 序， 整个排序过程可以递归进行，以此达到整个数据变成有序序列
 */
public class Quicksort {

    public static void main(String[] args) {
//        int[] arr = {-9, 78, 0, 23, -567, 70, -1, 900, 4561};
//        quickSort(arr, 0, arr.length-1);
//        System.out.println(Arrays.toString(arr));
        int[] arr = getArr();
        long start = System.currentTimeMillis();
        System.out.println("排序中...");
        quickSort(arr, 0, arr.length - 1);//这边耗时比冒泡小一些，主要是 ，减少了对数组的操作
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

    //快速排序 -O(n^2)
    public static void quickSort(int arr[], int left, int right) {
        int l = left;
        int r = right;
        int pivot = arr[(left + right) / 2];//选定一个中轴
        while (l < r) {
            while (arr[l] < pivot) {
                l++;
            }
            while (arr[r] > pivot) {
                r--;
            }
            if (l >= r) {
                break;
            }
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            //此时比r 下标大的都是大于等于pivot这个值的，所以让r--
            if (arr[l] == pivot) {
                r--;
            }
            if (arr[r] == right) {
                l++;
            }

        }
        //如果进行这个操作之后 相等了
        if (l == r) {
            r -= 1;
            l += 1;
        }
        if (l < right) {
            quickSort(arr, l, right);
        }
        if (r > left) {
            quickSort(arr, left, r);
        }
    }

}
