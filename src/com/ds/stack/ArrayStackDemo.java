package com.ds.stack;

import java.util.Scanner;

public class ArrayStackDemo {

    public static void main(String[] args) {
        ArrayStack<String> stack = new ArrayStack<>(5);
        //测试一下 ArrayStack 是否正确
        //先创建一个 ArrayStack 对象->表示栈

        String key = "";
        boolean loop = true; //控制是否退出菜单
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 表示添加数据到栈(入栈)");
            System.out.println("pop: 表示从栈取出数据(出栈)");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    String value = scanner.next();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        String res = stack.pop();
                        System.out.printf("出栈的数据是 %s\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~~");
    }
}


//自定义一个ArrayStack表示栈
class ArrayStack<T> {
    private int maxSize;//设置栈的最大容量
    private int top = -1;//表示栈
    private Object stack[];

    public ArrayStack(int maxSize) {
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
    public void push(T val) {
        if (isFull()) {
            System.out.println("栈已满，请勿添加");
            return;
        }
        top++;
        stack[top] = val;
    }

    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈已空");
        }
        T val = (T) stack[top];
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

}