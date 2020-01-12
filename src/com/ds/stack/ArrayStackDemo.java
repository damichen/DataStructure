package com.ds.stack;

import java.util.Scanner;

public class ArrayStackDemo {

    public static void main(String[] args) {
//        ArrayStack<String> stack = new ArrayStack<>(5);
        SingleLinedListStack<String> stack = new SingleLinedListStack<>("00",5);
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
                    NodeStack<String> nodeStack =new NodeStack<>(value,null);
                    stack.push(nodeStack);
                    break;
                case "pop":
                    try {
                        NodeStack res = stack.pop();
                        System.out.printf("出栈的数据是 %s\n", res.no);
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

class SingleLinedListStack<T> {

    public NodeStack<T> head;
    private int maxSize;//设置栈的最大容量

    public SingleLinedListStack(T val, int maxSize) {
        head = new NodeStack<T>(val, null);
        this.maxSize = maxSize;
    }

    /**
     * 栈满
     *
     * @return
     */
    public boolean isFull() {
        return size() == maxSize;
    }

    //栈空
    public boolean isEmpty() {
        return head == null || head.next == null;
    }

    public int size() {
        int top = 0;

        NodeStack cur = head.next;
        while (cur != null) {
            top++;
            cur = cur.next;
        }
        return top;
    }

    public void push(NodeStack val) {
        if (isFull()) {
            System.out.println("栈已满");
            return;
        }
        NodeStack cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = val;
    }

    public NodeStack pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈已空");
        }
        NodeStack cur = head.next;
        while (true) {
            if (cur.next != null && cur.next.next == null) {
                break;
            }
            cur = cur.next;
        }
        NodeStack temp = cur.next;
        cur.next = null;
        return temp;
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }
        NodeStack cur = head.next;
        while (true) {
            if (cur == null){
                break;
            }
            System.out.println(cur);
            cur = cur.next;
        }
    }
}

class NodeStack<T> {

    public T no;
    public NodeStack next;

    public NodeStack(T no, NodeStack next) {
        this.no = no;
        this.next = next;
    }

    @Override
    public String toString() {
        return "NodeStack{" +
                "no=" + no +
                '}';
    }
}