package com.atguigu.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        //先创建一个节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //创建一个链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);
        //显示一把
        singleLinkedList.list();
        System.out.println("------------------------");
        //测试修改节点的代码
        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~~");
        singleLinkedList.update(newHeroNode);
        //显示一把
        singleLinkedList.list();
        System.out.println("-------------------------");
        singleLinkedList.del(3);
        singleLinkedList.list();
        //测试一下单链表有效节点个数
        System.out.println(getLength(singleLinkedList.getHead()));
        //测试一下看看是否得到倒数第K个元素
        System.out.println("---------------");
        HeroNode res=findLastIndexNode(singleLinkedList.getHead(),4);
        System.out.println("res="+res);
        System.out.println("---------------");
        reversetList(singleLinkedList.getHead());
        singleLinkedList.list();
        System.out.println("测试逆序打印-------------");
        reversePrint(singleLinkedList.getHead());
    }
    //获取到单链表的节点的个数（如果是带头节点的链表，需求不统计头结点）

    /**
     *
     * @param head 链表的头节点
     * @return 返回的就是有效节点的个数
     */
    public static int getLength(HeroNode head){
        if (head.next==null){
            return 0;//空链表返回0
        }
        int length=1;
        //定义一个辅助的变量
        HeroNode cur=head.next;
        while (cur.next!=null){
            length++;
            cur=cur.next;
        }
        return length;
    }

    /*
    * 查找单链表中的倒数第K个节点
    * 1.编写一个方法，接收head节点，同时接收一个index
    * 2.index表示的是倒数第index个
    * 3.先把链表从头到尾遍历，得到链表的总的长度getLength
    * 4.得到size后，我们从链表的第一个开始遍历(size-index)个，就可以得到
    * 5.如果找到了，则返回该节点，否则返回null
    * */
    public static HeroNode findLastIndexNode(HeroNode head,int index){
        //判断如果链表为空，返回null
        if (head.next==null){
            return null;//没有找到
        }
        //第一个遍历得到链表的长度（节点个数）
        int size=getLength(head);
        //第二次遍历 size-index 位置，就是我们倒数的第k个节点
        //先做一个index的校验
        if (index<=0||index>size){
            return null;
        }
        //定义给辅助变量
        HeroNode cur=head.next;
        for (int i = 0; i < size - index; i++) {
            cur=cur.next;
        }
        return cur;
    }

    //将单链表反转
    public static void reversetList(HeroNode head){
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (head.next==null||head.next.next==null){
            return;
        }
        //定义一个辅助的指针(变量）,帮助我们遍历原来的链表
        HeroNode cur=head.next;
        HeroNode next=null;//指向当前节点[cur]的下一个节点
        HeroNode reversHead=new HeroNode(0,"","");
        //遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead的最前端
        while (cur!=null){
            next=cur.next;//先暂时保存当前节点的下一个节点，因为后面需要使用
            cur.next=reversHead.next;
            reversHead.next=cur;
            cur=next;
        }
        //将head.next指向reverseHead.next
        head.next=reversHead.next;
    }

    //使用方式2逆序打印（栈）
    public static void reversePrint(HeroNode head){
        if (head.next==null){
            return;
        }
        //创建要给一个栈，将各个节点压入栈
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur=head.next;
        //将链表中的节点压入栈
        while (cur!=null){
            stack.push(cur);
            cur=cur.next;
        }
        //将栈中的节点进行打印，pop出栈
        while (stack.size()>0){
            System.out.println(stack.pop());
        }
    }
}

//定义一个HeroNode,每个HeroNode对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int hNo, String hName, String hNickname) {
        this.no = hNo;
        this.name = hName;
        this.nickname = hNickname;
    }

    //为了显示方法，我们重新toString
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}

//定义一个SingleLinkedList 管理我们的英雄
class SingleLinkedList {
    //先初始化一个头结点，头结点不要动,不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    public void setHead(HeroNode head) {
        this.head = head;
    }

    //添加节点到单向链表
    /*
     *思路 当不考虑编号顺序时
     * 1.找到当前链表的最后节点
     * 2.将最后这个节点的next指向新的节点
     * */
    public void add(HeroNode heroNode) {
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        HeroNode temp = head;
        //遍历链表，找到最后
        while (true) {
            //找到链表的最后
            if (temp.next == null) {
                break;
            }
            //如果没有找到最后，就将temp后移
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    //第二种添加英雄的方式，根据排名将英雄插入到指定位置（如果有这个排名，则添加失败，并给出提示）
    public void addByOrder(HeroNode heroNode) {
        //因为头节点不能动，因此我们仍然通过一个辅助指针（变量）来帮助找到添加的位置
        //因为这是一个单链表，因此我们找的temp是位于添加位置的前一个节点，否则加入不了
        HeroNode temp = head;
        boolean flag = false;//flag标识添加的编号是否存在，默认为false
        while (true) {
            if (temp.next == null) {//说明temp已经在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no) {//位置找到了，就在temp的后面插入
                break;
            } else if (temp.next.no == heroNode.no) {//说明希望添加的heroNode的编号已经存在
                flag = true;//说明编号存在
                break;
            }
            temp = temp.next;//后移，遍历当前链表
        }
        //判断flag的值
        if (flag == true) {
            //不能添加，说明编号存在
            System.out.printf("准备插入的英雄的编号%d 已经存在了，不能加入\n", heroNode.no);
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //显示链表【遍历】
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true) {
            //判断是否到链表最后
            if (temp.next == null) {
                System.out.println(temp);
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            temp = temp.next;
        }
    }

    /*
    * 修改节点的信息，根据no编号来修改，即no编号不能改
    * 说明
    1.根据newHeroNode的no来修改即可
    * */
    public void update(HeroNode newHeroNode){
        //判断是否为空
        if (head.next==null){
            System.out.println("链表为空~");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroNode temp=head.next;
        boolean flag=false;
        while (true){
            if (temp==null){
                break;//已经遍历完链表
            }
            if (temp.no==newHeroNode.no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        //根据flag 判断是否找到要修改的节点
        if (flag){
            temp.name=newHeroNode.name;
            temp.nickname=newHeroNode.nickname;
        }else {
            //没有找到，提示信息
            System.out.printf("没有找到编号为%d的节点,不能修改\n",newHeroNode.no);
        }
    }

    /*
    * 删除节点
    * 思路
    * 1.head 不能动，因此我们需要一个temp辅助节点找到待删除节点的前一个节点
    * */
    public void del(int no){
        HeroNode temp = head;
        //标志是否找到待删除的节点
        boolean flag=false;
        while (true){
            if (temp.next==null){
                break;
            }
            if (temp.next.no==no){
                //找到待删除节点的前一个节点temp
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if (flag){
            //找到，可以删除了
            temp.next=temp.next.next;
        }else {
            System.out.printf("要删除的%d节点不存在\n",no);
        }
    }


}