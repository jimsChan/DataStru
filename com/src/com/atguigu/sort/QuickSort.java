package com.atguigu.sort;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[8000000];
        Random r=new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i]=r.nextInt();
        }
//        int[] arr={9,8,7,6,5,4,3,2,1};
        long start = System.currentTimeMillis();
        quickSort2(arr, 0, arr.length - 1);
        long end = System.currentTimeMillis();
        System.out.println("耗费时间:"+(end-start));
    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left;//左下标
        int r = right;//右下标
        int pivot = arr[(left + right) / 2];
        int temp = 0;//临时变量
        //while循环的目的是让比pivot值小放到左边
        //比pivot值打放到右边
        while (l < r) {
            //在pivot的左边一直找，找到大于等于pivot值才退出
            while (arr[l] < pivot) {
                l += 1;
            }
            while (arr[r] > pivot) {
                r -= 1;
            }
            //如果l>=r说明pivot的左右两边的值，已经安装左边全部小于等于pivot
            //而右边全部是大于等于pivot的值
            if (l >= r) {
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            //如果交换完后，发现这个arr[l]==pivot值，相等 --，前移
            if (arr[l] == pivot) {
                r -= 1;
            }
            //如果交换完后，发现这个arr[r]==pivot值，相等 ++，后移
            if (arr[r] == pivot) {
                l += 1;
            }
        }
        //如果l==r,必须l++,r--,否则会出现栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }
        //向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        //向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }
    }

    public static void quickSort2(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int base = arr[left];
        int i = left;
        int j = right;
        int temp = 0;
        while (i < j) {
            while (j > i && arr[j] >= base) {
                j--;
            }
            while (i < j && arr[i] <= base) {
                i++;
            }
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        arr[left]=arr[i];
        arr[i] = base;
        quickSort2(arr,left,i-1);
        quickSort2(arr,j+1,right);
    }
}
