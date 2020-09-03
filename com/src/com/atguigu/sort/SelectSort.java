package com.atguigu.sort;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1};
        selectSort(arr);
        System.out.println("第一轮后：");
        System.out.println(Arrays.toString(arr));
    }

    //选择排序
    //时间复杂度：O(n²)
    public static void selectSort(int[] arr) {
        //使用逐步推导的方式来
        //第一轮
        //原始的数组：101,34,119,1
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            arr[minIndex] = arr[i];
            arr[i] = min;
        }
    }
}
