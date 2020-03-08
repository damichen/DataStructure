package com.ds.search;

import java.util.Arrays;

/**
 * 斐波拉契查找算法
 */
public class FeibonacciSearch {
    //定义一个斐波那契数列最大的下标
    public static int maxSize = 20;

    public static void main(String[] args) {
        int arr[] = {11, 23, 45, 52, 67, 78, 90, 110};
        int searchIndex = feibonacciSearch(arr, 110);
        System.out.println("在数组的下标为" + searchIndex);
    }

    /**
     * 使用非递归的方式来生成斐波那契数列
     *
     * @return
     */
    public static int[] feib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
     * @param a   传入的数组
     * @param key 需要查找的值
     * @return 数组中的下标
     */
    public static int feibonacciSearch(int a[], int key) {
        int f[] = feib();
        int low = 0;
        int high = a.length - 1;
        int k = 0;
        int mid = 0;
        while (high > f[k] - 1) {
            k++;
        }
        //保证数组的长度正好等于斐波拉契数列的f[k],并且要保证数组有序，则需要将最后几个值赋值成原数组的最后一个值
        int temp[] = Arrays.copyOf(a, f[k]);
        System.out.println("临时数组：" + Arrays.toString(temp));
        for (int i = a.length; i < temp.length; i++) {
            temp[i] = a[a.length - 1];
        }
        while (low <= high) {
            System.out.println("斐波拉契~");
            mid = low + f[k - 1] - 1;//这样就正好在斐波拉契数列的黄金分割点上
            if (key < temp[mid]) {
                high = mid - 1;
                k--;//这里是在黄金分割点的前面部分，直接将数组定位为k-1那边就行了
            } else if (key > temp[mid]) {
                low = mid + 1;
                k -= 2;//这里需要说明一下的是f[k] = f[k-1] + f[k-2]; key>mid说明在黄金分割点的后边儿后边的长度为f[k-2]所以是k-2
            } else {
                //这里需要注意一下，前面为了让temp长度处于斐波拉契数列的长度，使得有些下标在原数组中是没有的；
                // 而且这个时候mid会大于high，这个时候high就是我们正确的下标
                if (mid > high) {
                    return high;
                } else {
                    return mid;
                }
            }

        }

        return -1;
    }

}
