package com.ds.linkedlist;

public class Josepfu {

    public static void main(String[] args) {
        CircleLinkedList circleLinkedList = new CircleLinkedList();
        circleLinkedList.addBoy(5);
        circleLinkedList.showBoy();

        circleLinkedList.countBoy(4,3,5);
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
            } else {
                curBoy.setNext(boy);
                curBoy = boy;
                curBoy.setNext(first);
            }
        }

    }

    // 根据用户的输入，计算出小孩出圈的顺序

    /**
     * @param startNo  表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums     表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数有误，请重新输入");
            return;
        }
        //先让这个节点指向最后一个值,这个辅助节点用删除的时候
        Boy helper = first;
        for (int i = 1; i < nums; i++) {
            helper = helper.getNext();
        }
        Boy curBoy = first;
        //让它的到初始值指定的位置
        for (int i = 1; i < startNo; i++) {
            helper = helper.getNext();
            curBoy = curBoy.getNext();
        }

        while (true) {
            if (helper == curBoy){
                break;
            }
            for (int i = 0; i < countNum - 1; i++) {
                curBoy = curBoy.getNext();
                helper = helper.getNext();
            }
            System.out.printf("第%d个小孩出圈\n", curBoy.getNo());
            curBoy = curBoy.getNext();
            helper.setNext(curBoy);
        }
        System.out.printf("最后一个编号%d小孩出圈\n", curBoy.getNo());

    }

    // 遍历当前的环形链表
    public void showBoy() {
        if (first == null) {
            System.out.println("环形链表中没有值");
            return;
        }
        Boy curBoy = first;
        while (true) {
            System.out.printf("当前小孩是第%d个小孩\n", curBoy.getNo());
            curBoy = curBoy.getNext();
            if (curBoy == first) {
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

