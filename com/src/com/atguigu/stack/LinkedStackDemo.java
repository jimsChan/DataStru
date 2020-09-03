package com.atguigu.stack;

import java.util.Stack;

public class LinkedStackDemo {
    public static void main(String[] args) {
        LinkedStack linkedStack = new LinkedStack(4);
        linkedStack.push(1);
        linkedStack.push(2);
        linkedStack.push(3);
        linkedStack.push(4);
        try {
            linkedStack.list();
            linkedStack.pop();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

class LinkedStack {
    private int max;//栈最大容量
    private int top;//栈顶
    private StackNode head=new StackNode(0,null,null);//头节点

    public LinkedStack(int maxSize) {
        this.max = maxSize;
        this.top = 0;
    }

    public StackNode getHead() {
        return head;
    }

    //栈是否为空
    public boolean isEmpty() {
        return top == 0;
    }

    //栈满
    public boolean isFull() {
        return max == top;
    }

    //入栈
    public void push(int value) {
        if (isFull()) {
            throw new RuntimeException("栈满，不能插入");
        }
        //找到最后节点并插入
        StackNode tmp = getHead();
        while (true) {
            if (tmp.getNext() == null) {
                tmp.setNext(new StackNode(value,tmp,null));
                top++;
                break;
            }
            tmp = tmp.getNext();
        }
    }

    //出栈
    public void pop(){
        if (isEmpty()){
            throw new RuntimeException("栈空,不能出栈");
        }
        //找到最后一个节点
        StackNode temp=getHead();
        while (true){
            if (temp.getNext()==null){
                System.out.printf("出栈:%d",temp.getValue());
                temp.getPre().setNext(null);
                break;
            }
            temp=temp.getNext();
        }
    }
    //遍历栈
    public void list(){
        if (isEmpty()){
            throw new RuntimeException("栈空，请先添加数据");
        }
        //1.反转链表，然后遍历，再反转
        reversList();
        StackNode temp = getHead().getNext();//获取第一个节点
        for (int i = 1; i < top; i++) {
            System.out.printf("栈值:%d\n",temp.getValue());
            temp=temp.getNext();
        }
        System.out.printf("栈值:%d\n",temp.getValue());
        reversList();
    }

    //反转链表
    public void reversList(){
        if (isEmpty()){
            throw new RuntimeException("栈空，请先添加数据");
        }
        StackNode head=getHead();
        StackNode temp=head.getNext();
        StackNode stack2=new StackNode(0,null,null);
        while (true){
            if (head.getNext()!=null){
                if (temp.getNext()==null){
                    //只有一个节点了
                    head.setNext(null);
                }else {
                    head.setNext(temp.getNext());
                    temp.getNext().setPre(head);
                }
                temp.setPre(stack2);
                temp.setNext(stack2.getNext());
                stack2.setNext(temp);
                temp=head.getNext();
            }else {
                break;
            }
        }
        head.setNext(stack2.getNext());
    }
}



class StackNode {
    private int value;//数据
    private StackNode pre;//前节点
    private StackNode next;//下一个节点

    //构造方法
    public StackNode(int value, StackNode pre, StackNode next) {
        this.value = value;
        this.pre = pre;
        this.next = next;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public StackNode getPre() {
        return pre;
    }

    public void setPre(StackNode pre) {
        this.pre = pre;
    }

    public StackNode getNext() {
        return next;
    }

    public void setNext(StackNode next) {
        this.next = next;
    }
}