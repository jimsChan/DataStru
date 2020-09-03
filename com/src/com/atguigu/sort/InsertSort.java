package com.atguigu.sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr={101,34,119,1};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    //插入排序
    public static void insertSort(int[] arr){
        //使用逐步推到的方式来讲解，便利理解
        //第一轮{101,34,119,1} = >{34,101,119,1}
        //给insertVal找到插入的位置
        //说明
        //1. insertIndex >=0保证在给insertVal 找插入位置，不越界
        //2. insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
        //3. 就需要将 arr[insertIndex]后移
        for (int i = 1; i < arr.length; i++) {
            //定义待插入的数
            int insertVal = arr[i];
            int insertIndex=i-1;
            while (insertIndex>=0 && insertVal<arr[insertIndex]){
                arr[insertIndex+1]=arr[insertIndex];
                insertIndex--;
            }
            arr[insertIndex+1]=insertVal;
        }

    }
}
