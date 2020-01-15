package com.ds.stack;

public class Calculator {

    public static void main(String[] args) {
        //根据前面老师思路，完成表达式的运算
        String expression = "7*2*2-5+1-5+3-4"; // 15//如何处理多位数的问题？
        //创建两个栈，数栈，一个符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义需要的相关变量
        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' '; //将每次扫描得到 char 保存到 ch
        String keepNum = ""; //用于拼接 多位数
        //开始 while 循环的扫描 expression
        while (true) {
            //依次得到 expression 的每一个字符
            ch = expression.substring(index, index+1).charAt(0);
            boolean isOper = operStack.isOper(ch);
            if (isOper) {
                if (operStack.isEmpty()){
                    operStack.push(ch);
                }
            }
        }

    }

}

//先创建一个栈,直接使用前面创建好
//定义一个 ArrayStack2 表示栈, 需要扩展功能
class ArrayStack2 {
    private int maxSize;//设置栈的最大容量
    private int top = -1;//表示栈
    private Object stack[];

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        if (maxSize <= 0) {
            System.out.println("请传入size大于0的数");
        }
        stack = new Object[this.maxSize];
    }

    /**
     * 栈满
     *
     * @return
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 添加到栈里边
     *
     * @param val
     */
    public void push(int val) {
        if (isFull()) {
            System.out.println("栈已满，请勿添加");
            return;
        }
        top++;
        stack[top] = val;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈已空");
        }
        int val = (int) stack[top];
        top--;
        return val;
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("栈已空");
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%s\n", i, stack[i] + "");
        }
    }

    //返回运算符的优先级，优先级是程序员来确定, 优先级使用数字表示
    //数字越大，则优先级就越高.
    public int priority(int oper) {
        if ('*' == oper || '/' == oper) {
            return 1;
        } else if ('+' == oper || '-' == oper) {
            return 0;
        }
        return -1;// 假定目前的表达式只有 +, - , * , /
    }

    //判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }
    public boolean isEqueal(char val) {
        return val == '=';
    }
    //计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0; // res 用于存放计算的结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num1 / num2;
                break;
        }
        return res;
    }
}