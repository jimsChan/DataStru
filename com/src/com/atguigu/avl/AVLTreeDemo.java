package com.atguigu.avl;

public class AVLTreeDemo {
    public static void main(String[] args) {
//        int[] arr = {4, 3, 6, 5, 7, 8};//左旋转测试数组
        int[] arr = {10, 12, 8, 9, 7, 6};//右旋转测试数组
        //创建一个AVLTree对象
        AVLTree avlTree = new AVLTree();
        //添加结点
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        //中序遍历
        System.out.println("中序遍历");
        avlTree.infixOrder();
        System.out.println("在没有平衡处理前~~~");
        System.out.println("树的左子树高度=" + avlTree.getRoot().left.height());
        System.out.println("树的右子树高度=" + avlTree.getRoot().right.height());

    }
}

//创建AVLTree
class AVLTree {
    private Node root;

    //添加结点的方法
    public void add(Node node) {
        if (root == null) {
            root = node;//如果root为空，则直接让root指向node
        } else {
            root.add(node);
        }
    }

    public Node getRoot() {
        return root;
    }

    //中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("空树");
        }
    }

    //查找要删除的节点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //查找父节点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    /**
     * @param node 传入的阶段（当做二叉排序树的根节点）
     * @return 返回的以node为根节点的二叉排序树的最小节点的值
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        //循环的查找左节点，就会找到最小值
        while (target.left != null) {
            target = target.left;
        }
        //这时target就指向了最小结点,删除最小结点
        delNode(target.value);
        return target.value;
    }

    //删除节点
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            //1.需要先去找到要删除的结点 targetNode
            Node targetNode = search(value);
            if (targetNode == null) {
                return;
            }
            //如果我们发现当前这颗二叉排序树只有一个节点
            if (root.left == null && root.right == null) {
                root = null;
            }
            //去找到targetNode的父节点
            Node parent = searchParent(value);
            //如果删除的节点是叶子节点
            if (targetNode.left == null && targetNode.right == null) {
                //判断targetNode 是父节点的左子节点，还是右子节点
                if (parent != null) {
                    if (parent.left != null && parent.left.value == value) {//是左子节点
                        parent.left = null;
                    } else if (parent.right != null && parent.right.value == value) {//是右子节点
                        parent.right = null;
                    }
                } else {
                    targetNode = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {//删除有两颗子树的节点
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;
            } else {//删除只有一颗子树的结点
                //如果要删除的结点有左子节点
                if (targetNode.left != null) {
                    if (parent != null) {
                        //如果targetNode是parent的左子节点
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else {//targetNode 是 parent 的右子节点
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {//删除的节点有右子节点
                    //如果targetNode是parent的右子节点
                    if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {//如果targetNode 是 parent的右子节点
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }
        }
    }


}

//创建Node结点
class Node {
    int value;
    Node left;
    Node right;

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public Node(int value) {
        this.value = value;
    }

    //添加结点的方法
    public void add(Node node) {
        //递归的形式添加结点，注意需要满足二叉排序树的要求
        if (node == null) {
            return;
        }
        //判断传入的节点的值，和当前子树的根节点的值关系
        if (node.value < this.value) {
            //如果当前节点左子节点为null
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {//添加的节点的值大于当前节点的值
            if (this.right == null) {
                this.right = node;
            } else {
                //递归的向右子节点添加
                this.right.add(node);
            }
        }
        //当添加完节点后，如果右子树的高度-左子树的高度>1，左旋转
        if (rightHeight() - leftHeight() > 1) {
            //如果它的右子树的左子树高度大于它的右子树的高度
            if (right != null && right.leftHeight() > right.rightHeight()) {
                right.rightRotate();
                leftRotate();
            } else {
                leftRotate();//左旋转
            }
            return;//必须要!!!
        }
        //当添加完节点后，如果左子树的高度-右子树的高度>1，右旋转
        if (leftHeight() - rightHeight() > 1) {
            //如果它的左子树的右子树高度大于它的左子树的高度
            if (left != null && left.rightHeight() > left.leftHeight()) {
                left.leftRotate();
                rightRotate();
            } else {
                rightRotate();//右旋转
            }
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * 查找要删除的结点
     *
     * @param value 希望删除的结点的值
     * @return 如果找到返回该节点，否则返回null
     */
    public Node search(int value) {
        if (value == this.value) {//找到就是该结点
            return this;
        } else if (value < this.value) {//如果查找的值小于当前节点，向左子树递归查找
            //如果左子节点为空
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {//如果查找的值不小于当前节点，向右子树递归查找
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     * 查找要删除节点的父节点
     *
     * @param value 要删除结点的值
     * @return 返回删除结点的父结点, 如果没有就返回null
     */
    public Node searchParent(int value) {
        //如果当前节点就是要删除的节点的父节点，就返回
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //如果查找的值小于当前结点的值，并且当前节点的左子节点不为空
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);//向左子树递归查找
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);//向右子树递归查找
            } else {
                return null;//没有找到父节点
            }
        }
    }

    //返回左子树的高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    //返回右子树的高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    //返回当前结点的高度，以该节点为跟节点的树的高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    //左旋转的方法
    private void leftRotate() {
        //创建新的节点，以当前根节点的值
        Node newNode = new Node(value);
        //把新的结点的左子树设置成当前节点的左子树
        newNode.left = left;
        //把新的结点的右子树设置成当前节点的右子树的左子树
        newNode.right = right.left;
        //把当前结点的值替换成右子节点的值
        value = right.value;
        //把当前节点的右子树设置成当前节点右子树的右子树
        right = right.right;
        //把当前节点的左子树（左子节点）设置成新的节点
        left = newNode;
    }

    //右旋转的方法
    private void rightRotate() {
        //创建新的节点，以当前根节点的值
        Node newNode = new Node(value);
        //把新的结点的左子树设置成当前节点的左子树
        newNode.right = right;
        //把新的结点的右子树设置成当前节点的右子树的左子树
        newNode.left = left.right;
        //把当前结点的值替换成右子节点的值
        value = left.value;
        //把当前节点的右子树设置成当前节点右子树的右子树
        left = left.left;
        //把当前节点的左子树（左子节点）设置成新的节点
        right = newNode;
    }
}