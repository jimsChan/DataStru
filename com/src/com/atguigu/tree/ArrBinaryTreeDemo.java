package com.atguigu.tree;

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder(0);
        System.out.println("\n中序遍历");
        arrBinaryTree.infixOrder(0);
        System.out.println("\n后序遍历");
        arrBinaryTree.postOrder(0);
    }
}

// 编写一个ArrayBinaryTree，实现顺序存储二叉树遍历
class ArrBinaryTree {
    private int[] arr;//存储数据节点的数组

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //编写一个方法，完成顺序存储二叉树的前序遍历
    public void preOrder(int index) {
        //如果数组为空，或者arr.length=0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
            return;
        }
        //输出当前元素
        System.out.print(arr[index] + "\t");
        //向左递归遍历
        if ((index * 2 + 1) < arr.length) {
            preOrder(2 * index + 1);
        }
        //向右递归
        if ((index * 2 + 2) < arr.length) {
            preOrder(2 * index + 2);
        }
    }

    //顺序存储中序遍历
    public void infixOrder(int index) {
        //如果数组为空，或者arr.length=0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
            return;
        }
        if ((index * 2 + 1) < arr.length) {
            infixOrder((index * 2 + 1));
        }
        System.out.print(arr[index] + "\t");
        if ((index * 2 + 2) < arr.length) {
            infixOrder(index * 2 + 2);
        }
    }

    //后序遍历(左右中)
    public void postOrder(int index){
        //判断树是否为空
        if (arr==null||arr.length==0){
            System.out.println("空树，不能遍历");
            return;
        }
        if ((index*2+1)<arr.length){
            postOrder((index*2+1));
        }
        if ((index*2+2)<arr.length){
            postOrder((index*2+2));
        }
        System.out.print(arr[index]+"\t");
    }
}
