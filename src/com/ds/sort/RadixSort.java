package com.ds.sort;

import java.util.Arrays;

//基数排序--O(nxk)
public class RadixSort {
    public static void main(String[] args) {
//        int arr[] = {53, 3, 542, 748, 14, 214,2,41,5224};
//        radixSort(arr);
//        System.out.println(Arrays.toString(arr));
        int[] arr = getArr();
        long start = System.currentTimeMillis();
        System.out.println("排序中...");
        radixSort(arr);
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
    //基数排序方法 -这里只支持了正数
    // 这里需要用到一个二维数组 ，如果数据量太大了，会内存溢出java.lang.OutOfMemoryError: Java heap space
    public static void radixSort(int[] arr) {
        //首先我们要得到最大的数 ，用来确定遍历的次数
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        //得到最大数值的长度
        int maxLen = (max + "").length();
        //定义一个二维数组，表示 10 个桶, 每个桶就是一个一维数组
        //说明
        //1. 二维数组包含 10 个一维数组
        //2. 为了防止在放入数的时候，数据溢出，则每个一维数组(桶)，大小定为 arr.length
        //3. 名明确，基数排序是使用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];
        //同时创建一个数组用来存放 bucket[i] 的个数
        int[] bucketElementCounts = new int[10];
        for (int i = 0, n = 1; i < maxLen; i++, n *= 10) {
            for (int j = 0; j < arr.length; j++) {
                int temp = (arr[j] / n) % 10;//分别求个，十，百，千...余数
                int count = bucketElementCounts[temp];
                bucket[temp][count] = arr[j];
                bucketElementCounts[temp] = count + 1;
            }

            //这样执行完之后，得到一个二维数组 ,不足位数的都在 bucket[0]里边 ,
            // 并且在bucketElementCounts数组中存放了对应下标中存放的个数
            int index = 0;
            for (int k = 0; k < bucket.length; k++) {
                int count = bucketElementCounts[k];
                if (count != 0) { //代表这个桶里边有数据才进去
                    for (int j = 0; j < count; j++) {
                        arr[index++] = bucket[k][j];
                    }
                }
                //第 i+0 轮处理后，需要将每个 bucketElementCounts[k] = 0 ！！！！
                bucketElementCounts[k] = 0;
            }
        }

    }
}
