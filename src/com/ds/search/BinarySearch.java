package com.ds.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找算法
 */
public class BinarySearch {

    public static void main(String[] args) {
        int findVal = 67;
        int arr[] = {11, 23, 45, 52, 57,57,57,57, 67, 78, 90, 110};
        int searchIndex = binarySearch(arr, 0, arr.length - 1, findVal);
        System.out.printf("%d數組下標为%d", findVal, searchIndex);
//        List<Integer> vals = binarySearch2(arr, 0, arr.length - 1, findVal);
//        System.out.println("找到" + findVal + "相同的下标为" + vals);
    }

    public static int binarySearch(int arr[], int left, int right, int findVal) {
        System.out.println("二分查找·..");
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (left > right) {
            return -1;
        }
        if (midVal > findVal) {
            return binarySearch(arr, left, mid - 1, findVal);
        } else if (midVal < findVal) {
            return binarySearch(arr, mid + 1, right, findVal);
        } else {
            return mid;
        }
    }

    public static List<Integer> binarySearch2(int arr[], int left, int right, int findVal) {
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (left > right) {
            return new ArrayList<>();
        }
        if (midVal > findVal) {
            return binarySearch2(arr, left, mid - 1, findVal);
        } else if (midVal < findVal) {
            return binarySearch2(arr, mid + 1, right, findVal);
        } else {
            ArrayList<Integer> findValList = new ArrayList<>();
            int temp = mid - 1;
            while (true) {
                if (temp < left || arr[temp] != arr[mid]) {
                    break;
                }
                findValList.add(temp);
                temp--;
            }
            findValList.add(mid);
            temp = mid + 1;
            while (true) {
                if (temp > right || arr[temp] != arr[mid]) {
                    break;
                }
                findValList.add(temp);
                temp++;
            }
            return findValList;
        }

    }

}
