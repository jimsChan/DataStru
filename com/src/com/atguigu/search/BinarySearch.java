package com.atguigu.search;

import java.util.ArrayList;

//用二分查找的前提是数组有序
public class BinarySearch {
    public static void main(String[] args) {
        int arr[] = {1, 8, 89, 1000, 1234};

        System.out.println(binarySearch(arr, 0, arr.length, 1));
    }

    /**
     * 二分查找算法
     *
     * @param arr     数组
     * @param left    左边的索引
     * @param right   右边的索引
     * @param findVal 要查找的值
     * @return 如果找到就返回下标，如果没有找到就返回-1
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        //当left>right时，说明找不到，退出递归
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) {
            //向右递归
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            //向左递归
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }

    /**
     * {1, 8, 89, 1000,1000,1000 1234};
     * 思考题：有多个额相同的值时，如何将所有的数值都查找到，比如这里的1000
     * 思考分析：
     * 1.在找到mid索引值，不要马上返回
     * 2.向mid索引值的左边扫描，将所有满足1000的元素下标加入到ArrayList中
     * 3.向mid索引值的右边扫描，将所有满足1000的元素下标加入到ArrayList中
     * 4.将ArrayList返回
     */
    public static ArrayList binarySearch2(int[] arr, int left, int right, int findVal) {
        //当left>right时，说明找不到，退出递归
        if (left > right) {
            return new ArrayList<Integer>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) {
            //向右递归
            return binarySearch2(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            //向左递归
            return binarySearch2(arr, left, mid - 1, findVal);
        } else {
            ArrayList<Integer> resIndexlist = new ArrayList<>();
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {
                    break;
                }
                resIndexlist.add(temp);
                temp -= 1;
            }
            resIndexlist.add(mid);
            temp = mid + 1;
            while (true) {
                if(temp > arr.length-1 || arr[temp] != findVal){
                    break;
                }
                resIndexlist.add(temp);
                temp += 1;
            }
            return resIndexlist;
        }
    }
}
