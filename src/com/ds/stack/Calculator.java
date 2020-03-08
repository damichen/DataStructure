package com.ds.stack;

public class Calculator {

    public static void main(String[] args) {
        //根据前面老师思路，完成表达式的运算
        String expression = "7*2*2-5+1-5+3-4+100"; // 15//如何处理多位数的问题？
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
//            ch = expression.substring(index, index + 1).charAt(0);
            ch = expression.charAt(index);
            boolean isOper = operStack.isOper(ch);
            if (isOper) {
                if (operStack.isEmpty()) {
                    operStack.push(ch);
                } else {
                    // 如果符号栈有操作符，就进行比较,如果当前的操作符的优先级小于或者等于栈中的操作符,
                    // 就需要从数栈中 pop 出两个数,
                    // 在从符号栈中 pop 出一个符号，进行运算，将得到结果，入数栈，然后将当前的操作符入符
                    // 号栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        numStack.push(res);
                        operStack.push(ch);
                    } else {
                        //如果当前的操作符的优先级大于栈中的操作符， 就直接入符号栈.
                        operStack.push(ch);
                    }
                }
            } else {
                //如果是数，则直接入数栈
                //numStack.push(ch - 48); //? "1+3" '1' => 1
                //分析思路
                //1. 当处理多位数时，不能发现是一个数就立即入栈，因为他可能是多位数
                //2. 在处理数，需要向 expression 的表达式的 index 后再看一位,如果是数就进行扫描，如果是符号才入栈

                //3. 因此我们需要定义一个变量 字符串，用于拼接
                //处理多位数
                keepNum += ch;
                //如果 ch 已经是 expression 的最后一位，就直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    //判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符，则入栈
                    //注意是看后一位，不是 index++
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        //如果后一位是运算符，则入栈 keepNum = "1" 或者 "123"
                        numStack.push(Integer.parseInt(keepNum));
                        //重要的!!!!!!, keepNum 清空
                        keepNum = "";
                    }
                }
                //让 index + 1, 并判断是否扫描到 expression 最后.

            }
            index++;
            if (index >= expression.length()) {
                break;
            }
        }
        System.out.println("stack:");
        numStack.list();
        System.out.println("stackop:");
        operStack.list();
        //当表达式扫描完毕，就顺序的从 数栈和符号栈中 pop 出相应的数和符号，并运行.
        while (true) {
            //如果符号栈为空，则计算到最后的结果, 数栈中只有一个数字【结果】
            if (!operStack.isEmpty()) {
                num1 = numStack.pop();
                num2 = numStack.pop();
                oper = operStack.pop();
                res = operStack.cal(num1, num2, oper);
                numStack.push(res);
            } else {
                break;
            }
        }
        int res2 = numStack.pop();
        System.out.printf("表达式%s=%d\n", expression, res2);
    }

}

//先创建一个栈,直接使用前面创建好
//定义一个 ArrayStack2 表示栈, 需要扩展功能
class ArrayStack2 {
    private int maxSize;//设置栈的最大容量
    private int top = -1;//表示栈
    private int stack[];

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        if (maxSize <= 0) {
            System.out.println("请传入size大于0的数");
        }
        stack = new int[this.maxSize];
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

    //
    public int peek() {
        return stack[top];
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