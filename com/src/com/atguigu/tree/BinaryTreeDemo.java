package com.atguigu.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        //先需要创建一个二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");
        HeroNode node6 = new HeroNode(6, "aaa");
        HeroNode node7 = new HeroNode(7, "bbb");
        //说明，我们先手动创建该二叉树，后面我们学习递归的方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        node2.setLeft(node6);
        node2.setRight(node7);
        //测试
//        System.out.println("前序遍历");
        binaryTree.setRoot(root);
//        binaryTree.preOrder();//1234
//
//        System.out.println("中序遍历");
//        binaryTree.infixOrder();//2134
//
//        System.out.println("后序遍历");
//        binaryTree.postOrder();//2431
//
//        System.out.println("前序遍历查找");
//        HeroNode res = binaryTree.infixOrderSearch(4);
//        if (res != null) {
//            System.out.printf("找到了,信息为:no=%d name=%s", res.getNo(), res.getName());
//        } else {
//            System.out.printf("没有找到no = %d 的英雄", 5);
//        }

        System.out.println("删除前，前序遍历");
        binaryTree.preOrder();
        binaryTree.delNode(6);
        System.out.println("删除后，前序遍历");
        binaryTree.preOrder();

    }
}

//定义二叉树
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
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
    public void delNode(int no){
        if (root!=null){
            if (root.getNo()==no){
                root=null;
            }else {
                root.delNode(no);
            }
        }else {
            System.out.println("空栈不能删除");
        }
    }
}


//先创建HeroNode节点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;//默认null
    private HeroNode right;//默认null

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
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
    public void delNode(int no){
        /**
         * 1.因为我们的二叉树是单向的，所以我们是判断当前节点的子节点是否需要删除节点，而不能去判断当前这个节点是不是需要删除节点
         * 2.如果当前节点的左子节点不为空，并且左子节点就是要删除节点，就将this.left=null;并且就返回（结束递归删除）
         * 3.如果当前节点的右子节点不为空，并且右子节点就是要删除节点，就将this.right=null;并且就返回（结束递归删除）
         * 4.如果第2和第3步没有删除结点，那么我们就需要向左子树进行递归删除
         * 5.如果第4步也没有删除结点，则应该向右子树进行递归删除
         */
        if (this.left!=null&&this.left.no==no){
            this.left=null;
            return;
        }
        if (this.right!=null&&this.right.no==no){
            this.right=null;
            return;
        }
        if (this.left!=null){
            this.left.delNode(no);
        }
        if (this.right!=null){
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
