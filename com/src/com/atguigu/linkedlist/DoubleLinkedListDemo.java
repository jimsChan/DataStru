package com.atguigu.linkedlist;

import java.util.TreeMap;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        //先创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        //创建一个链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
//        doubleLinkedList.add(hero1);
//        doubleLinkedList.add(hero2);
//        doubleLinkedList.add(hero3);
//        doubleLinkedList.add(hero4);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.addByOrder(hero1);

        doubleLinkedList.list();

        System.out.println("修改---------------");
        doubleLinkedList.update(new HeroNode2(4,"公孙胜","入云龙"));
        doubleLinkedList.list();
        System.out.println("删除---------------");
        doubleLinkedList.del(3);
        doubleLinkedList.list();
    }
}

//创建一个双向链表的类
class DoubleLinkedList{
    //先初始化一个头结点，头结点不要动,不存放具体的数据
    private HeroNode2 head = new HeroNode2(0, "", "");

    //返回头节点
    public HeroNode2 getHead() {
        return head;
    }

    //遍历双向链表
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，因此我们需要一个辅助变量来遍历
        HeroNode2 temp = head.next;
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

    //添加节点到双向链表的最后
    public void add(HeroNode2 heroNode) {
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        HeroNode2 temp = head;
        //遍历链表，找到最后
        while (true) {
            //找到链表的最后
            if (temp.next == null) {
                break;
            }
            //如果没有找到最后，就将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        //将最后这个节点的next指向新的节点
        //形成一个双向链表
        temp.next = heroNode;
        heroNode.pre=temp;
    }

    //修改一个节点的内容,可以看到双向链表的节点内容修改和单向链表一样，只是节点类型改成HeroNode2
    public void update(HeroNode2 newHeroNode){
        //判断是否为空
        if (head.next==null){
            System.out.println("链表为空~");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroNode2 temp=head.next;
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

    //从双向链表中删除节点
    public void del(int no){
        HeroNode2 temp = head;
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
            if (temp.next.next!=null){
                temp.next=temp.next.next;
                temp.next.pre=temp;
            }else {
                temp.next=null;
            }
        }else {
            System.out.printf("要删除的%d节点不存在\n",no);
        }
    }

    /**
     * 双向链表的按顺序添加
     * 说明
     * 1.遍历双向链表，当下一个节点大于添加节点则插入
     */
    public void addByOrder(HeroNode2 heroNode) {
        //因为头节点不能动，因此我们仍然通过一个辅助指针（变量）来帮助找到添加的位置
        //因为这是一个单链表，因此我们找的temp是位于添加位置的前一个节点，否则加入不了
        HeroNode2 temp = head;
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
            if (temp.next==null){
                temp.next=heroNode;
                heroNode.pre=temp;
            }else {
                heroNode.pre=temp;
                heroNode.next=temp.next;
                temp.pre=heroNode;
                temp.next=heroNode;
            }
        }
    }
}

//定义HeroNode2,每个HeroNode对象就是一个节点
class HeroNode2{
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;//指向下一个节点，默认为null
    public HeroNode2 pre;//指向前一个节点，默认为null
    public HeroNode2(int hNo, String hName, String hNickname) {
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
