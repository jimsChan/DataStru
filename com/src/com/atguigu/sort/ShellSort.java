package com.atguigu.sort;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort2(arr);
        System.out.println(Arrays.toString(arr));
    }

    //使用逐步推到的方式来编写希尔排序
    //希尔排序时
    //1.对有序序列在插入时采用交换法，并测试排序速度
    public static void shellSort(int[] arr) {
        int temp = 0;
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                //遍历各组中所有的元素（共5组，每组有2个元素）,步长5
                for (int j = i - gap; j >= 0; j -= gap) {
                    //如果当前元素大于加上步长后的那个元素，说明交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            count++;
            System.out.println("希尔排序第" + count + "轮:" + Arrays.toString(arr));
        }

        //希尔排序第一轮排序
        //因为第一轮排序，是将10个数据分成5组
//        for (int i = 5; i < arr.length; i++) {
//            //遍历各组中所有的元素（共5组，每组有2个元素）,步长5
//            for (int j = i - 5; j >= 0; j -= 5) {
//                //如果当前元素大于加上步长后的那个元素，说明交换
//                if (arr[j] > arr[j + 5]) {
//                    int temp = arr[j];
//                    arr[j] = arr[j + 5];
//                    arr[j + 5] = temp;
//                }
//            }
//        }
//        System.out.println("希尔排序1轮后=" + Arrays.toString(arr));
//        for (int i = 2; i < arr.length; i++) {
//            //遍历各组中所有的元素（共2组，每组有5个元素）,步长5
//            for (int j = i - 2; j >= 0; j -= 2) {
//                //如果当前元素大于加上步长后的那个元素，说明交换
//                if (arr[j] > arr[j + 2]) {
//                    int temp = arr[j];
//                    arr[j] = arr[j + 2];
//                    arr[j + 2] = temp;
//                }
//            }
//        }
//        System.out.println("希尔排序2轮后=" + Arrays.toString(arr));
//        //第三轮
//        for (int i = 1; i < arr.length; i++) {
//            //遍历各组中所有的元素（共2组，每组有5个元素）,步长5
//            for (int j = i - 1; j >= 0; j -= 1) {
//                //如果当前元素大于加上步长后的那个元素，说明交换
//                if (arr[j] > arr[j + 1]) {
//                    int temp = arr[j];
//                    arr[j] = arr[j + 1];
//                    arr[j + 1] = temp;
//                }
//            }
//        }
//        System.out.println("希尔排序3轮后=" + Arrays.toString(arr));
    }

    //2.对有序序列在插入时采用移动法，并测试排序速度
    public static void shellSort2(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从第gap个元素，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        //移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    //当退出while后，就给temp找到插入的位置
                    arr[j]=temp;
                }
            }
        }
    }
}
