package com.atguigu.Algorithm.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

//马踏棋盘问题
public class HorseChessboard {

    private static int X;//棋盘的列数
    private static int Y;//棋盘的行数
    //创建一个数组，标记棋盘的各个位置是否被访问过
    private static boolean visited[];
    //使用一个属性，标记是否棋盘的所有位置都被访问
    private static boolean finished;//如果为true，表示成功

    public static void main(String[] args) {
        //测试骑士周游算法是否正确
        X = 8;
        Y = 8;
        int row = 1;//马儿初始位置的行，从1开始编号
        int colum = 1;//马儿初始位置的列，从1开始编号
        //创建棋盘
        int[][] chessboard = new int[X][Y];
        visited = new boolean[X * Y];//初始值都是false
        //测试一下
        long start = System.currentTimeMillis();
        traversalChessborad(chessboard, row - 1, colum - 1, 1);
        long end = System.currentTimeMillis();
        System.out.println("共耗时: " + (end - start) + "毫秒");
        //输出棋盘的最后情况
        for (int[] rows : chessboard) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 完成骑士周游问题的算法
     * @param chessboard 棋盘
     * @param row        代表马儿当前所在的行 从0开始
     * @param colum      马儿当前的位置的列 从0开始
     * @param step       第几步，出师位置就是第1步
     */
    public static void traversalChessborad(int[][] chessboard, int row, int colum, int step) {
        chessboard[row][colum] = step;
        visited[row * X + colum] = true;//标记该位置已经访问
        //获取当前位置可以走的下一个位置的集合
        ArrayList<Point> ps = next(new Point(colum, row));
        //对ps进行排序,排序的规则就是对ps的所有的Point对象的下一步的位置的数目，进行排序
        sort(ps);
        //遍历ps
        while (!ps.isEmpty()) {
            Point p = ps.remove(0);//取出下一个可以走的位置
            //判断该点是否已经访问过
            if (!visited[p.y * X + p.x]) {//说明没有访问过
                traversalChessborad(chessboard, p.y, p.x, step + 1);
            }
        }
        //判断马儿是否完成了任务，使用step 和应该走的步数比较，
        //如果没有达到数量，则表示没有完成任务，将整个棋盘直0
        if (step < X * Y && !finished) {
            chessboard[row][colum] = 0;
            visited[row * X + colum] = false;
        } else {
            finished = true;
        }
    }

    /**
     * 功能：根据当前的位置（point），计算马儿还能走哪些位置（point），并放入到一个集合中（ArrayList），最多有8个位置
     *
     * @param curPoint
     * @return
     */
    public static ArrayList<Point> next(Point curPoint) {
        //创建一个ArrayList
        ArrayList<Point> ps = new ArrayList<>();
        //创建一个point
        Point p1 = new Point();
        //5
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //6
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //7
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //0
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //1
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        //2
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //3
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //4
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        return ps;
    }

    //根据当前这个异步的所有的下一步的选择位置，进行非递减排序,减少回溯次数
    public static void sort(ArrayList<Point> ps){
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                //获取到o1的下一步的所有位置个数
                int count1 = next(o1).size();
                int count2 = next(o2).size();
                if (count1<count2){
                    return -1;
                }else if(count1==count2){
                    return 0;
                }else {
                    return 1;
                }
            }
        });
    }
}
