package com.ds.queue;

import java.util.Scanner;

/**
 * 队列的优化--环形队列
 */
public class CircleArrayQueueDemo{

    public static void main(String[] args) {
        CircleArrayQueue queue = new CircleArrayQueue(4);//次数组有一个字段是预留为空的字段，所以这里可用容量为3
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show)代表显示全部");
            System.out.println("g(get)代表出队列");
            System.out.println("a(add)代表加数据");
            System.out.println("h(head)代表显示队列头部");
            System.out.println("e(exit)代表退出");
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
                        System.out.println("获取队列："+num);
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
                        int head = queue.headQueue();
                        System.out.printf("arr[%d]=%d\n", queue.getFront(), head);
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

class CircleArrayQueue {
    private int maxSize;//表示数组最大容量
    private int front;//front变量的含义做一个调整：front就指向队列的第一个元素，也就是说arr[front]就是队列的第一个元素，front的初始值 =0
    private int rear;//rear变量的含义做一个调整：rear指向队列的最后一个元素的后一个位置，因为希望空出一个空间作为约定 ，rear 的初始值 =0
    private int arr[];//该数组用于存放数据，模拟队列

    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    public boolean isFull() {
        return (rear+1)%maxSize == front;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public void addQueue(int n) {
        if (isFull()) {
            throw new RuntimeException("队列已满!!!");
        }
        arr[rear] = n;
        rear = (rear+1)%maxSize;//将rear指向下一个元素
    }

    public int getSize(){
        return (rear-front+maxSize)%maxSize;//这里+maxSize就是为了使它一直是正数
    }

    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空!!");
        }
        int val = arr[front];
        front = (front+1)%maxSize;
        return val;
    }

    public int getFront() {
        return front;
    }

    public int getRear() {
        return rear;
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空!!");
        }
        for (int i = front; i < front+getSize(); i++) {
            System.out.printf("arr[%d]=%d\n", i%maxSize, arr[i%maxSize]);
        }
    }

    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空!!");
        }
        return arr[front];
    }

}
