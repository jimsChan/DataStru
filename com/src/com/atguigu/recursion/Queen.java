package com.atguigu.recursion;

public class Queen {
    //定义最大皇后数
    int max = 8;
    //定义数组array，保存皇后放置位置的结果，比如 arr={0,4,7,5,2,6,1,3}
    int[] arr = new int[max];

    public static void main(String[] args) {
        Queen queen = new Queen();
        queen.check(0);
    }

    //判断皇后是否冲突
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            //如果同一列有皇后就冲突
            //或者两个皇后在同一斜线上就冲突
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {
                return false;
            }
        }
        return true;
    }

    //打印
    private void print(){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    //放置皇后
    private void check(int n){
        if (n==8){//n==8说明是第九个皇后了
            print();
            return;
        }
        for (int i = 0; i < max; i++) {
            arr[n]=i;
            if (judge(n)){
                check(n+1);
            }
            //如果冲突，就继续执行array[n]=i;即将第n个皇后，放置在本行后移的一个位置
        }
    }
}
