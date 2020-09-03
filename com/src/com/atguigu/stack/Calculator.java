package com.atguigu.stack;

public class Calculator {
    public static void main(String[] args) {
        //根据前面思路，完成表达式的运算
        String experssion="3+2*6-2";
        //创建2个栈,一个数栈，一个符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        int index=0;//用于扫描
        int num1=0;
        int num2=0;
        int oper=0;
        int res=0;
        char ch=' ';//将每次扫描得到的char保存到ch
        String keepNum="";
        //开始while循环的扫描experssioin
        while (true){
            //依次得到expression的每一个字符
            ch=experssion.substring(index,index+1).charAt(0);
            //判断ch是什么，然后做出相应处理
            if (operStack.isOper(ch)){
                //如果是运算符
                //判断当前的符号栈是否为空
                if (!operStack.isEmpty()){
                    //处理
                    if (operStack.priority(ch)<=operStack.priority(operStack.peek())){
                        num1=numStack.pop();
                        num2=numStack.pop();
                        oper=operStack.pop();
                        res=numStack.cal(num1,num2,oper);
                        //把运算的结果入数栈
                        numStack.push(res);
                        operStack.push(ch);
                    }else {
                        operStack.push(ch);
                    }
                }else {
                    //如果为空直接入符号栈
                    operStack.push(ch);
                }
            }else {
                keepNum+=ch;
                //判断下一个字符是不是数字，如果是数字，就唏嘘扫描，如果是运算符，别入栈
                if (index==experssion.length()-1){
                    numStack.push(Integer.parseInt(keepNum));
                }else {
                    numStack.push(Integer.parseInt(keepNum));
                    keepNum="";
                }
            }
            //让index+1，并判断是否扫描到expression最后
            index++;
            if (index>=experssion.length()){
                break;
            }
        }
        while (true){
            //如果符号栈为空，则计算到最后的结果，数栈中只有1个数字
            if (operStack.isEmpty()){
                break;
            }
            num1=numStack.pop();
            num2=numStack.pop();
            oper=operStack.pop();
            res=numStack.cal(num1,num2,oper);
            numStack.push(res);
        }
        System.out.printf("表达式%s=%d",experssion,numStack.pop());
    }
}

//先创建一个一个栈，直接用前面创建好的
//定义一个ArrayStack2，需要扩展功能
class ArrayStack2 {
    private int maxSize; //栈的大小
    private int[] stack; //数组，数组模拟栈，数据就放在该数组
    private int top = -1; //top表示栈顶，初始化为-1

    //构造器
    public ArrayStack2(int maxSize){
        this.maxSize=maxSize;
        stack=new int[this.maxSize];
    }

    //栈满
    public boolean isFull(){
        return top==maxSize-1;
    }

    //栈空
    public boolean isEmpty(){
        return top==-1;
    }

    //入栈-push
    public void push(int value){
        if (isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top]=value;
    }

    //出栈-pop，将栈顶的数据返回
    public int pop(){
        //先判断栈是否为空
        if (isEmpty()){
            //抛出异常
            throw new RuntimeException("栈空，没有数据~");
        }
        int value=stack[top];
        top--;
        return value;
    }

    //遍历栈
    public void list(){
        if (isEmpty()){
            //抛出异常
            throw new RuntimeException("栈空，没有数据~");
        }
        for (int i=top;i>=0;i--){
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }

    //返回栈顶的数据，但不出栈
    public int peek(){
        return stack[top];
    }

    //返回运算符的优先级，优先级是程序员来确定的，优先级使用数字表示
    //数字越大，则优先级就越高
    public int priority(int oper){
        if (oper=='*'||oper=='/'){
            return 1;
        }else if(oper=='+'||oper=='-'){
            return 0;
        }else {
            return -1;//假定目前的表达式只有+-*/,
        }
    }
    //判断是不是一个运算符
    public boolean isOper(char val){
        return val=='+'||val=='-'||val=='*'||val=='/';
    }
    //计算方法
    public int cal(int num1,int num2,int oper){
        int res=0;//res用于存放计算的结果
        switch (oper){
            case '+':
                res= num1+num2;
                break;
            case '-':
                res=num2-num1;
                break;
            case '*':
                res=num1*num2;
                break;
            case '/':
                res=num2/num1;
                break;
            default:
                break;
        }
        return res;
    }
}