package com.ds.queue;

import java.util.Scanner;

/**
 * 此处代码使用一次就不能复用
 * 接下来优化：将数组改进成为一个环形的数组 取模：%
 */
public class ArrayQueueDemo {

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show)代表显示全部");
            System.out.println("g(get)代表出队列");
            System.out.println("a(add)代表加数据");
            System.out.println("h(head)代表显示队列头部");
            System.out.println("q(quit)代表退出");
            int key = sc.next().charAt(0);
            switch (key) {
                case 's':
                    try {
                        queue.showQueue();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'g':
                    try {
                        int num = queue.getQueue();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'a':
                    System.out.println("请输入一个数字");
                    int n = sc.nextInt();
                    try {
                        queue.addQueue(n);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'h':
                    try {
                        queue.headQueue();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'q':
                    sc.close();
                    loop = false;
                    break;
            }
        }
    }

}

class ArrayQueue {
    private int maxSize;//表示数组最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int arr[];//该数组用于存放数据，模拟队列

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1;//将队列头部初始化-1
        rear = -1;//将队列尾部初始化-1
    }

    public boolean isFull() {
        return rear == maxSize-1;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public void addQueue(int n) {
        if (isFull()) {
            throw new RuntimeException("队列已满!!!");
        }
        rear++;
        arr[rear] = n;
    }

    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空!!");
        }
        front++;
        System.out.printf("arr[%d]=%d\n", front, arr[front]);
        return arr[front];
    }

    public void showQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空!!");
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空!!");
        }
        System.out.printf("arr[%d]=%d\n", front+1, arr[front+1]);
        return arr[front+1];
    }

}