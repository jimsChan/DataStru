package com.atguigu.linkedlist;

import java.util.Stack;

public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.add("jack");
        stack.add("tom");
        stack.add("smith");

        //出栈
        while (stack.size()>0){
            String pop = stack.pop();
            System.out.println(pop);
        }

    }
}
