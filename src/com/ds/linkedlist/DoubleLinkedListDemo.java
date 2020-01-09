package com.ds.linkedlist;

public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        HeroNode2 node1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 node2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 node3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 node4 = new HeroNode2(4, "公孙胜", "入云龙");
        HeroNode2 node5 = new HeroNode2(5, "关胜", "大刀");
        HeroNode2 node6 = new HeroNode2(6, "林冲", "豹子头");
        HeroNode2 node7 = new HeroNode2(7, "秦明", "霹雳火");
        HeroNode2 node33 = new HeroNode2(3, "没用", "智少星");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        //新增
//        doubleLinkedList.add(node1);
//        doubleLinkedList.add(node2);
//        doubleLinkedList.add(node3);
//        doubleLinkedList.add(node4);
//        doubleLinkedList.add(node5);

        doubleLinkedList.addByOrder(node1);
        doubleLinkedList.addByOrder(node4);
        doubleLinkedList.addByOrder(node2);
        doubleLinkedList.addByOrder(node3);
        doubleLinkedList.addByOrder(node5);
        doubleLinkedList.list();
        //删除
//        System.out.println("-----------执行删除操作之后-----------");
//        doubleLinkedList.del(5);
//        doubleLinkedList.list();
//        System.out.println();
//        doubleLinkedList.del(2);
//        doubleLinkedList.list();
//        System.out.println();
//        doubleLinkedList.del(1);
//        doubleLinkedList.list();
        //修改
//        System.out.println("-----修改之后-----");
//        doubleLinkedList.update(node33);
//        doubleLinkedList.list();


    }

}

class DoubleLinkedList {
    public HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    //添加节点到单向链表
    //思路，当不考虑编号顺序时
    //1. 找到当前链表的最后节点
    //2. 将这个节点的next 指向 新的节点
    //3. 将新的节点的pre 指向temp
    public void add(HeroNode2 heroNode) {
        HeroNode2 temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置
    //(如果有这个排名，则添加失败，并给出提示)
    //1.找到当前节点的下一个节点,这样在遍历完之后temp不是null
    public void addByOrder(HeroNode2 heroNode) {
        HeroNode2 temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            } else if (temp.next.no == heroNode.no) {
                flag = true;
                break;
            } else if (temp.next.no>heroNode.no){
                break;
            }
            temp = temp.next;
        }
        if (flag){
            System.out.println("链表中已存在此节点");
        }else {
            heroNode.pre = temp;
            heroNode.next = temp.next;
            if (temp.next!=null){
                temp.next.pre = heroNode;
            }
            temp.next = heroNode;

        }
    }

    //修改节点的信息, 根据no编号来修改，即no编号不能改.
    //说明
    //1. 根据 newHeroNode 的 no 来修改即可
    public void update(HeroNode2 newHeroNode) {
        if (head.next == null) {
            System.out.println("此链表为空");
            return;
        }
        if (newHeroNode == null) {
            System.out.println("传入节点不能为空");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp.no == newHeroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        } else {
            System.out.println("没有找到此节点");
        }
    }

    //删除节点
    //思路
    //1. head 不能动，找到该节点，让这个节点的 用temp存起来,使用cur.pre.next = temp.next;
    //2. 这里需要注意的是如果删除的是最后一个节点，cur.next==null需要判断一下
    //3. cur.next.pre = temp.pre;
    public void del(int no) {
        if (head.next == null) {
            return;
        }
        HeroNode2 cur = head.next;
        while (cur != null) {
            if (cur.no == no) {
                HeroNode2 temp = cur;
                cur.pre.next = temp.next;
                if (cur.next != null) {
                    cur.next.pre = temp.pre;
                }
                break;
            }
            cur = cur.next;
        }
    }

    //显示链表[遍历]
    public void list() {
        if (head.next == null) {
            System.out.println("此链表为空");
            return;
        }
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class HeroNode2 {
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HearoNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
//                ", next=" + next +
                '}';
    }
}