package com.ds.tree;

import java.util.Arrays;
import java.util.Random;

/**
 * 堆排序（八大排序中-用树树实现）
 */
public class HeapSort {
    public static void main(String[] args) {
        //要求将数组进行升序排序
        int arr[] = {4, 6, 8, 5, 9,-23,23,4,2,54,532,98};
//        int maxSize = 800 * 10000;
//        int[] arr = new int[maxSize];
//        for (int i = 0; i < maxSize; i++) {
//            arr[i] = (int)(Math.random()*100000);
//        }
//        System.out.println(Arrays.toString(arr));
        Long start = System.currentTimeMillis();
        heapSort(arr);
        Long endTime = System.currentTimeMillis();
        System.out.println("总共花费了"+(endTime-start)+"ms");
    }

    public static void heapSort(int arr[]) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int temp = 0;
        System.out.println("堆排序开始!!");
        //1)这里就讲数组调整成为大顶堆{9,6,8,5,4}
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        /*
        * 2).将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
          3).重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换
             步骤，直到整个序列有序。
        */
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];//大顶堆的元素是最大的放后边
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }
//        System.out.println("排序之后的数组为:" + Arrays.toString(arr));
        System.out.println(arr.length);
    }

    //将一个数组(二叉树), 调整成一个大顶堆

    /**
     * 功能： 完成 将 以 i 对应的非叶子结点的树调整成大顶堆
     * 举例 int arr[] = {4, 6, 8, 5, 9}; => i = 1 => adjustHeap => 得到 {4, 9, 8, 5, 6}
     * 如果我们再次调用 adjustHeap 传入的是 i = 0 => 得到 {4, 9, 8, 5, 6} => {9,6,8,5, 4}
     *
     * @param arr    待调整的数组
     * @param i      表示非叶子结点在数组中索引  （以该点为父节点，调整成大顶堆）
     * @param length 表示对多少个元素继续调整， length 是在逐渐的减少
     */
    public static void adjustHeap(int arr[], int i, int length) {
        int temp = arr[i];
        //这边k = 2*k+1代表指向下一个节点
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;//大顶堆是需要比较父节点和两个子节点的最大值出来，这里就代表子节点中比较大的值
            }
            if (temp < arr[k]) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;//这边是由于，我们在最开始就是从arr.length/2-1开始，然后将整个数组构成了一个大顶堆，所以我们之后的调动影响的就交换的那几个数
            }
        }
        arr[i] = temp;
    }

}
