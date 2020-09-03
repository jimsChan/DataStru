package com.atguigu.search;

import java.util.Arrays;

public class FibonacciSearch {
    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 1000, 1234};
        int i = fibSearch(arr, 1234);
        System.out.println(i);
    }

    //因为后面我们mid=low+F(k-1)-1,需要使用到斐波拉契数列，因此我们需要先获取到一个斐波那契数列
    //用非递归方法得到一个斐波那契数列
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }
    //编写斐波那契查找算法

    /**
     * 使用非递归方式写算法
     *
     * @param a   数组
     * @param key 我们需要查找的关键码
     * @return 返回对应的下标，如果没有返回-1
     */
    public static int fibSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int k = 0;//表示斐波那契分割数值的下标
        int mid = 0;
        int f[] = fib();//获取到斐波那契数列
        //获取到斐波那契分割数值的下标
        while (a.length > f[k] - 1) {
            k++;
        }
        //因为f[k]值可能大于数组a的长度，因此我们需要使用Array类，构造一个新的数组，并指向a[]
        //不足部分用0填充
        int[] temp = Arrays.copyOf(a, f[k]);
        //实际上需要使用a数组最后的数填充temp
        for (int i = high; i < temp.length; i++) {
            temp[i] = a[high];
        }
        //使用while循环处理找到我们的数key
        while (low <= high) {//只要满足Low<=high就可以找
            //mid是索引，f[k-1]就是f[k]全数组的左边大部分数的个数,low+f[k-1]=中间索引左边的个数
            mid = low + f[k - 1] - 1;//每次取斐波那契数列中的某个值时(F[k])，都会进行-1操作，这是因为有序表数组位序从0开始的，纯粹是为了迎合位序从0开始。
            if (key < temp[mid]) {//说明应该继续向数组前面查找
                high = mid - 1;
                //为什么是K--
                //说明：
                //1.全部元素=前面元素+后面元素
                //2.f[k]=f[k-1]+f[k-2]
                //因为，前面有f[k-1]个元素，所以可以继续拆分f[k-1]=f[k-2]+f[k-3]
                //即 在f[k-1]的前面继续查找K--
                //即下次循环mid=f[k-1-1]-1
                k--;
            } else if (key > temp[mid]) {//我们应该继续向数组的后面查找
                low = mid + 1;
                //为什么是k-=2
                //说明：
                //1.全部元素=前面的元素+后面的元素
                //2.f[k]=f[k-1]+f[k-2]
                //3.因为后面我们有f[k-2]所以可以继续拆分f[k-1]=f[k-3]+f[k-4]
                //4.即在f[k-2]的前面进行查找k-=2
                //5.即下次循环mid=f[k-1-2]-1
                k -= 2;
            } else {
                //找到
                //需要确定，返回的是哪个下标
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }
}
