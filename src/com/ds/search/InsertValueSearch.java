package com.ds.search;

/**
 * 插值查找算法
 */
public class InsertValueSearch {

    public static void main(String[] args) {
//        int arr[] = new int[100];
//        for (int i = 0; i < 100; i++) {
//            arr[i] = i;
//        }
//        int searchIndex = insertValueSearch(arr, 0, 99, 78);
        int arr[] = {11, 23, 45, 52, 57,57,57,57, 67, 78, 90, 110};
        int searchIndex = insertValueSearch(arr,0,arr.length-1,67);
        System.out.println("查找的数在数组下标的第"+searchIndex+"。");
    }

    /**
     * 差值查找法，这个也要求传入数组是有序的
     * @param arr 传入数组
     * @param left 左边边界
     * @param right 右边边界
     * @param findVal 需要查找的数
     * @return
     */
    public static int insertValueSearch(int arr[], int left, int right, int findVal) {
        System.out.println("插值查找...");
        if (left > right || arr[0] > findVal || arr[arr.length - 1] < findVal) {
            return -1;
        }
        int mid = left + (right-left)*(findVal - arr[left])/(arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal>midVal){
            return insertValueSearch(arr,mid+1,right,findVal);
        }else if (findVal<midVal){
            return insertValueSearch(arr,left,mid-1,findVal);
        }else {
            return mid;
        }
    }

}
