package com.atguigu.tree.threadedbinarytree;

import javax.xml.transform.Source;
import java.sql.SQLOutput;

//线索化二叉树
//中序遍历结果=>{8,3,10,1,14,6}
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        //测试中序线索二叉树的功能
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");

        //二叉树，后面我们要递归创建，现在简单处理使用手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        //测试线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.preThreadedNode(root);
        //测试:以10号节点做测试
        System.out.println("8的l节点:"+node4.getLeft());
        System.out.println("8的r节点:"+node4.getRight());
        System.out.println("10的l节点:"+node5.getLeft());
        System.out.println("10的r节点:"+node5.getRight());
        System.out.println("14的l节点:"+node6.getLeft());
        System.out.println("14的r节点:"+node6.getRight());
        System.out.println("6的l节点:"+node3.getLeft());
        System.out.println("6的r节点:"+node3.getRight());
    }
}

//先创建HeroNode结点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;//默认null
    private HeroNode right;//默认null

    //说明
    //1.如果leftType==0表示指向的是左子树，如果1则表示指向前驱结点
    //2.如果rightType==0表示指向右子树，如果1表示指向后继结点
    private int leftType;
    private int rightType;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //递归删除节点
    //1.如果删除的节点是叶子节点，则删除该节点
    //2.如果删除的节点是非叶子节点，则删除该子树
    public void delNode(int no) {
        /**
         * 1.因为我们的二叉树是单向的，所以我们是判断当前节点的子节点是否需要删除节点，而不能去判断当前这个节点是不是需要删除节点
         * 2.如果当前节点的左子节点不为空，并且左子节点就是要删除节点，就将this.left=null;并且就返回（结束递归删除）
         * 3.如果当前节点的右子节点不为空，并且右子节点就是要删除节点，就将this.right=null;并且就返回（结束递归删除）
         * 4.如果第2和第3步没有删除结点，那么我们就需要向左子树进行递归删除
         * 5.如果第4步也没有删除结点，则应该向右子树进行递归删除
         */
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.delNode(no);
        }
        if (this.right != null) {
            this.right.delNode(no);
        }
    }


    //编写前序遍历方法
    public void preOrder() {
        System.out.println(this);//先输出父节点
        //递归左子树
        if (this.left != null) {
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder() {
        //递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        //输出父节点
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    /**
     * 先序遍历查找
     *
     * @param no 查找的no
     * @return 如果找到就返回该node, 如果没有就返回null
     */
    public HeroNode preOrdersearch(int no) {
        if (this.no == no) {
            return this;
        }
        //判断当前节点的左子节点是否为空，如果不为空，则递归前序查找
        //如果左递归前序查找，找到节点，则返回
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrdersearch(no);
        }
        if (resNode != null) {//说明左子树找到了
            return resNode;
        }
        //1.左递归前序查找，找到节点则返回，否则继续判断
        //2.当前的节点的右子节点是否为空，如果不空，则继续向右递归前序查找
        if (this.right != null) {
            resNode = this.right.preOrdersearch(no);
        }
        return resNode;
    }

    /**
     * 中序遍历查找(左中右)
     *
     * @param no 查找的no
     * @return 如果找到就返回该node, 如果没有就返回null
     */
    public HeroNode infixOrdersearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrdersearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            resNode = this.right.infixOrdersearch(no);
        }
        return resNode;
    }

    /**
     * 后序遍历（左右中）
     *
     * @param no 查找的no
     * @return 如果找到就返回该node, 如果没有就返回null
     */
    public HeroNode postOrdersearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrdersearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.postOrdersearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.no == no) {
            return this;
        }
        return resNode;
    }
}

//定义ThreadedBinaryTree 实现了线索化功能的二叉树
class ThreadedBinaryTree {
    private HeroNode root;
    //为了实现线索化，需要创建一个指向当前节点的前驱节点的指针
    //在递归进行线索化时，pre总是保留前一个节点
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void infixThreadedNodes() {
        this.infixThreadedNodes(root);
    }

    //先序线索化二叉树
    public void preThreadedNode(HeroNode node) {
        if (node == null) {
            return;
        }
        if (node.getLeft()==null){
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre!=null&&pre.getRight()==null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre=node;
        if (node.getLeftType()==0){
            preThreadedNode(node.getLeft());
        }
        if (node.getRightType()==0){
            preThreadedNode(node.getRight());
        }
    }

    //先序遍历线索化二叉树
    public void preThreadedList() {
        HeroNode node = root;
        while (node != null) {
            System.out.println(node);
            while (node.getLeftType() == 0) {
                node = node.getLeft();
                System.out.println(node);
            }
            while (node.getRightType() == 1) {
                node = node.getRight();
            }
            node = node.getRight();
        }

    }

    /**
     * 编写对二叉树进行中序线索化的方法
     * @param node 当前需要线索化的节点
     */
    public void infixThreadedNodes(HeroNode node) {
        //如果node==null,不能线索化
        if (node == null) {
            return;
        }
        //(一)先线索化左子树
        infixThreadedNodes(node.getLeft());
        //(二)线索化当前节点
        //处理当前节点的前驱节点
        if (node.getLeft() == null) {
            //让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针类型，指向前驱节点
            node.setLeftType(1);
        }
        //处理后继节点
        if (pre != null && pre.getRight() == null) {
            //让前驱节点的右指针指向当前结点
            pre.setRight(node);
            //修改前驱结点的右指针类型
            pre.setRightType(1);
        }
        pre = node;
        //(三)再线索化
        infixThreadedNodes(node.getRight());
    }

    //中序遍历线索化二叉树的方法
    public void infixThreadedList() {
        //定义一个变量，存储当前遍历的结点，从root开始
        HeroNode node = root;
        while (node != null) {
            //循环的找到leftType==1的结点，第一个找到就是8结点
            //后面随着遍历而变化，因为当lefttype==1时，说明该结点是按照线索化处理后的有效节点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            //打印当前这个节点
            System.out.println(node);
            //如果当前节点的右指针指向的是后继节点，就一直输出
            while (node.getRightType() == 1) {
                //获取到当前节点的后继节点
                node = node.getRight();
                System.out.println(node);
            }
            //替换这个遍历的节点
            node = node.getRight();
        }
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树空，不遍历");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树空，不能遍历");
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树空，不能遍历");
        }
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrdersearch(no);
        } else {
            return null;
        }
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrdersearch(no);
        }
        return null;
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrdersearch(no);
        }
        return null;
    }

    //删除结点
    public void delNode(int no) {
        if (root != null) {
            if (root.getNo() == no) {
                root = null;
            } else {
                root.delNode(no);
            }
        } else {
            System.out.println("空栈不能删除");
        }
    }
}