package com.ds.linkedlist;

public class Josepfu {

    public static void main(String[] args) {
        CircleLinkedList circleLinkedList = new CircleLinkedList();
        circleLinkedList.addBoy(25);
        circleLinkedList.showBoy();
    }

}

class CircleLinkedList {

    private Boy first = null;//用于记录链表的首节点

    // 添加小孩节点，构建成一个环形的链表
    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("nums值不正确");
            return;
        }
        Boy curBoy = null;
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                curBoy = boy;
                boy.setNext(first);
            }else {
                curBoy.setNext(boy);
                curBoy = boy;
                curBoy.setNext(first);
            }
        }

    }

    // 遍历当前的环形链表
    public void showBoy() {
        if (first==null){
            System.out.println("环形链表中没有值");
            return;
        }
        Boy curBoy = first;
        while (true){
            System.out.printf("当前小孩是第%d个小孩\n",curBoy.getNo());
            curBoy = curBoy.getNext();
            if (curBoy == first){
                break;
            }
        }
    }
}

class Boy {
    private int no;//编号
    private Boy next;//指向下一个节点

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}

