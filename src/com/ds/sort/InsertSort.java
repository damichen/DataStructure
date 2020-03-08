package com.ds.sort;

import java.util.Arrays;

//插入排序 --直接插入排序
public class InsertSort {
    public static void main(String[] args) {
//        int[] arr = {101, 34, 119, 1, -1, 89};
//        insertSort(arr);
//        System.out.println(Arrays.toString(arr));
        int[] arr = getArr();
        long start = System.currentTimeMillis();
        System.out.println("排序中...");
        insertSort(arr);//这边耗时比冒泡小一些，主要是 ，减少了对数组的操作
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
    //插入排序 ， 讲述中分为两边 ，左边开始就一个值 ，就是arr[0] O(n^2)
    //这边有个明显的缺点就是如果需要插入的值比较小的时候 while 循环的次数就会增多
    public static void insertSort(int[] arr) {
        int insertIndex = 0;
        int insertValue = 0;
        //这里从1 开始就是 先在左边放一个值 ，从第2个值开始，比它大就放它右边，小就放左边
        for (int i = 1; i < arr.length; i++) {
            insertIndex = i - 1;
            insertValue = arr[i];
            //遍历找出对应的位置
            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                //后移就行了
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //将这个值插入到对应的位置 , 在这里的条件是arr[insertIndex]<=insertValue
            //所以直接将insertValue插入到
            arr[insertIndex + 1] = insertValue;
        }
    }

}
