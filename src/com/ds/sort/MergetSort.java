package com.ds.sort;

import java.util.Arrays;

//归并算法--分治  --O(nlogn) -时间复杂度为线性对数阶
public class MergetSort {
    public static void main(String[] args) {
//        int arr[] =  { 8, 4, 5, 7, 1, 3, 6, 2 }; //
        int arr[] =getArr();
        int temp[] = new int[arr.length];
        long start = System.currentTimeMillis();
        System.out.println("排序中...");
        mergeSort(arr,0,arr.length-1,temp);
        long end = System.currentTimeMillis();
        System.out.println("排序完成，共花费: " + (end - start) + "毫秒");
    }
    private static int[] getArr() {
        int len = 50 * 10000;
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 10000);
        }
        return arr;
    }
    public static void mergeSort(int arr[], int left, int right, int temp[]) {
        //这里注意是if ，在里边执行递归 ，自然就拆分了
        if (left < right) {
            int mid = (left + right) / 2;
            //继续向左拆分 ， 拆完之后小的数组里边就剩一个数字或者两个数字
            //比如说{ 8, 4, 5, 7, 1, 3, 6, 2 } -> 递归到{8,4}开始回溯(下标left=0,right=1)
            //这个时候右边也拆分不了了，就合并 {4,8, 5, 7, 1, 3, 6, 2 }
            mergeSort(arr, left, mid, temp);
            //向右拆分，
            mergeSort(arr, mid + 1, right, temp);
            //拆过的数据合并
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 合并--‘治’
     *
     * @param arr   原数组
     * @param left  左边下标
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  临时数组
     */
    public static void merge(int arr[], int left, int mid, int right, int temp[]) {
        int i = left;
        int j = mid + 1;
        int t = 0;//临时数组的下标

        while (i <= mid && j <= right) {
            if (arr[i] > arr[j]) {
                temp[t] = arr[j];
                t++;
                j++;
            } else {
                temp[t] = arr[i];
                t++;
                i++;
            }

        }
        //剩下的都是数字比较大的，并且有顺序，这个时候直接放到数组的最下边就行了
        while (j <= right) {
            temp[t++] = arr[j++];
        }
        while (i <= mid) {
            temp[t++] = arr[i++];
        }
        //完事之后将排序的数组拷贝到原数组中去
        t = 0;
        int tempL = left;//从原数组的哪个位置开始
        while (tempL <= right) {
            arr[tempL++] = temp[t++];
        }
    }
}
