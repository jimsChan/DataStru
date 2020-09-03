package com.atguigu.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        //完成中缀表达式转成后缀表达式的功能
        //说明
        //1. 1+((2+3)*4)-5 => 转成 1 2 3 + 4 x + 5 -
        //2. 因为直接对str 进行操作，不方便，因此 先将 “1+((2+3)x4)-5”中缀的表达式对应的list
        //即 "1+((2+3)*4)-5"=> ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
        //将得到的中缀表达式转换成对应的后缀表达式
        String expression = "1+((2+3)*4)-5";
        List<String> strings = toInfixExpressionList(expression);
        System.out.println(strings);
        System.out.println("======逆波兰=======");
        List<String> strings1 = parseSuffixExpressionList(strings);
        System.out.println(strings1);
        System.out.println("======逆波兰End=======");
        //先定义给逆波兰表达式
        //(3+4)×5-6 => 3 4 + 5 × 6 -
        //为了方便，逆波兰表达式的数字和符号使用空格隔开
        String suffixExpression = "3 4 + 5 * 6 -";
        //思路
        //1.现将表达式装到ArrayList中
        //2.将ArrayList 传递给一个方法，配合栈完成计算

        List<String> rpnList = getListString(suffixExpression);
        System.out.println("rpnList=" + rpnList);

        int res = calculate(rpnList);
        System.out.println("结果:" + res);
    }

    public static List<String> parseSuffixExpressionList(List<String> ls) {
        //定义2个栈
        Stack<String> s1 = new Stack<>();//符号栈
        //因为s2这个栈在整个转换过程中，没有pop操作，而且还要逆序输出
        //因此不用栈，直接使用List
        ArrayList<String> s2 = new ArrayList<>();
        //遍历ls
        for (String item : ls) {
            //如果是一个数,加入s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                //如果是“）”，则依次弹出s1栈顶的运算符，并压入s2,直到遇到左括号为止，此时将这一对括号丢弃掉
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();//将（弹出s1栈,消除小括号
            } else {
                //当item的优先级小于等于栈顶运算符的优先级
                while (s1.size() != 0 && Operation.getValue(s1.peek())>=Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                //还需要将item压入栈中
                s1.push(item);
            }
        }
        //将s1中剩余的运算符一次弹出并加入到s2
        while (s1.size()!=0){
            s2.add(s1.pop());
        }
        return s2;//因为是存放到List,因此按顺序输出就是对应的后缀表达式
    }

    //方法:将中缀表达式转成对应的List
    public static List<String> toInfixExpressionList(String s) {
        //先定义一个List,存放对应的数据内容
        List<String> ls = new ArrayList<>();
        int i = 0;//正是一个指针，用于遍历 中缀表达式 字符串
        String str;//用来做多位数拼接工作
        char c;//每遍历到一个字符就放入c中
        do {
            //如果c是一个非数字，就需要加入到ls
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                i++;//i要后移
            } else {//如果是一个数，需要考虑多位数
                str = "";//先将str 置成空串
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;//拼接
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;//返回
    }

    //将一个逆波兰表达式，依次将数据和运算符放入到ArrayList中
    public static List<String> getListString(String suffixExpression) {
        //将suffixExpression分割
        String[] split = suffixExpression.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    //完成对逆波兰表达式的运算
    public static int calculate(List<String> ls) {
        //创建一个栈，只需要一个栈即可
        Stack<String> stack = new Stack<>();
        //遍历ls
        for (String item : ls) {
            //这里使用正则表达式取出数
            if (item.matches("\\d+")) {
                //入栈
                stack.push(item);
            } else {
                //pop出2个数并运算
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                //把res入栈
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}

//编写一个类Operation 可以返回一个运算符 对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法，返回对应的优先级数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }
}