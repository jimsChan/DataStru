package com.atguigu.Algorithm.dac;

//汉诺塔
public class Hanoitower {
    public static void main(String[] args) {
        hanoiTower(3,'A','B','C');
    }

    /**
     * 使用分治算法汉,诺塔的移动的方法
     * @param num 盘子的总数
     * @param a 要移动盘子当前所在的柱子
     * @param b 用于中转的柱子
     * @param c 要移动的盘子最终所放置的柱子
     */
    public static void hanoiTower(int num, char a, char b, char c) {
        //如果只有一个盘
        if (num == 1) {
            System.out.println("第1个盘从 " + a + "->" + c);
        }else {
            //如果我们有n>=2情况，我们总是可以看做是两个盘 1.最下边一个盘 2.上面的所有盘
            //1.先把最上面的所有盘A->B,移动过程会使用到c
            hanoiTower(num-1,a,c,b);
            //2.把最下边的盘A->C
            System.out.println("第"+num+"个盘从 "+a+"->"+c);
            //3.把B塔的所有盘从B->C,移动过程使用到a塔
            hanoiTower(num-1,b,a,c);
        }
    }
}
