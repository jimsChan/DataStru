package com.atguigu.recursion;

public class Queen8 {
    //定义一个max表示共有多少个皇后
    int max = 8;
    int index=1;
    //定义数组array，保存皇后放置位置的结果，比如 arr={0,4,7,5,2,6,1,3}
    int[] array = new int[max];

    public static void main(String[] args) {
        //测试一把，8皇后是否正确
        Queen8 queen8 = new Queen8();
        queen8.check(0);
    }

    //写一个方法，可以将皇后摆放的位置输出
    private void print() {
        System.out.print("第"+index+"组：");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        index++;
        System.out.println();
    }

    /**
     * 查看当我们放置第n个皇后，就去监测该皇后是否和前面已经摆放的皇后冲突
     *
     * @param n 标识第n个皇后
     * @return
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            //说明：
            //1.array[i]==array[n] 表示判断第n个皇后是否和前面的n-1个皇后在同一列
            //2.Math.abs(n-i)==Math.abs(array[n]-array[i])表示判断第n个皇后是否和第i个皇后是否在同一斜线
            //证明在同一斜线：(y2-y1)=(x2-x1)=tan90°
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    //编写一个方法，放置第n个皇后
    //特别注意：check是每一次递归时，进入到check中都有for (int i = 0; i < max; i++),因此会有回溯
    private void check(int n){
        if (n == max){// n = 8,其实8个已然放好了
            print();
            return;
        }
        //依次放入皇后并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前这个皇后 n，放到该行的第1列
            array[n]=i;
            //判断当放置第n个皇后到i列时，是否冲突
            if (judge(n)){
                //接着放n+1个皇后,即开始递归
                check(n+1);
            }
            //如果冲突，就继续执行array[n]=i;即将第n个皇后，放置在本行后移的一个位置
        }
    }
}
