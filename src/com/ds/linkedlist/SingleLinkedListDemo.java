package com.ds.linkedlist;


/**
 * 单链表
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        HeroNode node1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode node2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode node3 = new HeroNode(3, "吴用", "智多星");
        HeroNode node4 = new HeroNode(4, "公孙胜", "入云龙");
        HeroNode node5 = new HeroNode(5, "关胜", "大刀");
        HeroNode node6 = new HeroNode(6, "林冲", "豹子头");
        HeroNode node7 = new HeroNode(7, "秦明", "霹雳火");
        HeroNode node22 = new HeroNode(2, "小炉子", "玉麒麟super");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(node1);
//        singleLinkedList.add(node2);
//        singleLinkedList.add(node3);
//        singleLinkedList.add(node4);

        singleLinkedList.addByOrder(node2);
        singleLinkedList.addByOrder(node1);
        singleLinkedList.addByOrder(node4);
        singleLinkedList.addByOrder(node3);
        singleLinkedList.addByOrder(node7);
        singleLinkedList.addByOrder(node5);
        singleLinkedList.addByOrder(node6);

        System.out.println("添加之后数据:");
        singleLinkedList.list();
       /* System.out.println("修改后的数据----");
        singleLinkedList.update(node22);
        singleLinkedList.list();
        System.out.println("有效个数:" + getLength(singleLinkedList.getHead()));
        System.out.println("-----开始执行删除操作----");
        singleLinkedList.del(1);
        singleLinkedList.list();
        System.out.println("有效个数:" + getLength(singleLinkedList.getHead()));
        System.out.println();
        singleLinkedList.del(2);
        singleLinkedList.list();
        System.out.println("有效个数:" + getLength(singleLinkedList.getHead()));
        System.out.println();
        singleLinkedList.del(4);
        singleLinkedList.list();
        System.out.println("有效个数:" + getLength(singleLinkedList.getHead()));
        System.out.println();
        singleLinkedList.del(3);
        singleLinkedList.list();*/
        HeroNode lastIndexNode = findLastIndexNode(singleLinkedList.getHead(), 2);
        System.out.println("查找单链表中的倒数第 k 个结点" + lastIndexNode);

        reversetList(singleLinkedList.getHead());
        singleLinkedList.list();

    }

    /**
     * 获取到单链表的节点的个数(如果是带头结点的链表，需求不统计头节点)
     *
     * @param head 链表的头节点
     * @return 返回有效节点的个数
     */
    public static int getLength(HeroNode head) {
        HeroNode temp = head;
        if (temp.next == null) {
            return 0;
        }
        int count = 0;
        while (true) {
            //这里从第一个开始，得跳过头节点，这个也可以写外边，然后whil(temp!=null)
            temp = temp.next;
            if (temp == null) {
                break;
            }
            count++;
        }
        return count;
    }

    /**
     * 查找单链表中的倒数第 k 个结点
     * //思路
     * //1. 编写一个方法，接收 head 节点，同时接收一个 index
     * //2. index 表示是倒数第 index 个节点
     * //3. 先把链表从头到尾遍历，得到链表的总的长度 getLength
     * //4. 得到 size 后，我们从链表的第一个开始遍历 (size-index)个，就可以得到
     * //5. 如果找到了，则返回该节点，否则返回 nulll
     *
     * @param head
     * @param index
     * @return
     */
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        if (head == null || head.next == null) {
            System.out.println("链表为空");
            return null;
        }
        int length = getLength(head);
        if (index <= 0 || index > length) {
            return null;
        }
        int reverseIndex = length - index;

        //这里如果 temp = head.next,下边就i<reverseIndex
        HeroNode temp = head;
        for (int i = 0; i <= reverseIndex; i++) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 单链表的反转
     *      此处需要说明的是节点用的都是相同的一堆节点，
     *      把heroNode中的next改掉了就是把所有head中的改掉了
     * 此处思路：
     *      1.创建一个头节点
     *      2.将原链表的节点进行遍历，并且在遍历过程中
     *      将每次来遍历来的节点的next指向 tempHead.next,
     *      将tempHead.next节点指向cur;再将cur = next(之前cur.next);
     *      （这样就是将遍历来的节点插入到最前面）
     *      3.将head指向tempHead.next(即指向新的节点)
     * @param head
     */
    public static void reversetList(HeroNode head) {
        if (head.next == null || head.next.next == null) {
            return;
        }
        HeroNode tempHead = new HeroNode(0, "", "");
        //定义一个辅助的指针(变量)，帮助我们遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null;// 指向当前节点[cur]的下一个节点
        //遍历原来的链表，每遍历一个节点，就将其取出，
        // 并放在新的链表 reverseHead 的最前端
        while (cur!=null) {
            next = cur.next;//先暂时保存当前节点的下一个节点，因为后面需要使用
            cur.next = tempHead.next;//将 cur 的下一个节点指向新的链表的最前端
            tempHead.next = cur; //将 cur 连接到新的链表上
            cur = next;//让 cur 后移
        }
        //将 head.next 指向 reverseHead.next , 实现单链表的反转
        head.next = tempHead.next;
    }

}

//定义SingleLinkedList 管理我们的英雄
class SingleLinkedList {
    HeroNode head = new HeroNode(0, "", "");

    //返回头节点
    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    //思路，当不考虑编号顺序时
    //1. 找到当前链表的最后节点
    //2. 将最后这个节点的next 指向 新的节点
    public void add(HeroNode heroNode) {
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置
    //(如果有这个排名，则添加失败，并给出提示)
    public void addByOrder(HeroNode heroNode) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > heroNode.no) {
                break;
            } else if (temp.next.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.printf("此节点已存在,%d\n", heroNode.no);
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改节点的信息, 根据no编号来修改，即no编号不能改.
    //说明
    //1. 根据 newHeroNode 的 no 来修改即可
    public void update(HeroNode newHeroNode) {
        HeroNode temp = head;
        if (temp.next == null) {
            System.out.println("此链表为空");
        }
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
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
            System.out.printf("没有找到 编号 %d 的节点，不能修改\n", newHeroNode.no);
        }
    }

    //删除节点
    //思路
    //1. head 不能动，因此我们需要一个temp辅助节点找到待删除节点的前一个节点
    //2. 说明我们在比较时，是temp.next.no 和  需要删除的节点的no比较
    public void del(int no) {
        HeroNode temp = head;
        if (temp.next == null) {
            System.out.println("此链表为空");
        }
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("没有找到 编号 %d 的节点，不能删除\n", no);
        }

    }

    //显示链表[遍历]
    public void list() {
        if (head.next == null) {
            System.out.println("此链表为空");
            return;
        }
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.printf("%d\t%s\t%s\n", temp.no, temp.name, temp.nickName);
            temp = temp.next;
        }
    }

    /*    *//**
     * 链表中有效节点的个数
     * @return
     *//*
    public int size(){
        HeroNode temp = head;
        if (temp.next == null){
            return 0;
        }
        int count=0;
        while (true){
            if (temp.next == null){
                break;
            }
            temp = temp.next;
            count++;
        }
        return count;
    }*/

}


class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode(int no, String name, String nickName) {
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