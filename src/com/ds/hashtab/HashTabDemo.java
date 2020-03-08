package com.ds.hashtab;

import java.util.Scanner;

public class HashTabDemo {
    public static void main(String[] args) {
        //创建哈希表
        HashTab hashTab = new HashTab(7);

        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("add:  添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建 雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.find(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }

}

/**
 * 雇员类
 */
class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

/**
 * 链表
 */
class EmpLinkList {
    public Emp head;

    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    public void list(int no) {
        if (head == null) {
            System.out.println("第" + (no + 1) + "条链表为空");
            return;
        }
        Emp curEmp = head;
        System.out.printf("第%d条链表\t", no + 1);
        while (true) {
            System.out.printf("[id=%d,name=%s]\t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    public Emp find(int id) {
        if (head == null) {
            return null;
        }
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {
                return curEmp;
            }
            if (curEmp.next == null) {
                return null;
            }
            curEmp = curEmp.next;
        }
    }
}

class HashTab {
    private EmpLinkList[] empLinkListArr;
    private int size;

    public HashTab(int size) {
        this.size = size;
        empLinkListArr = new EmpLinkList[size];
        for (int i = 0; i < size; i++) {
            empLinkListArr[i] = new EmpLinkList();
        }
    }

    public void add(Emp emp) {
        if (emp==null){
            System.out.println("emp空指针异常");
            return;
        }
        int empLinkListNO = hashTab(emp.id);
        empLinkListArr[empLinkListNO].add(emp);
    }

    public void list(){
        for (int i = 0; i < empLinkListArr.length; i++) {
            empLinkListArr[i].list(i);
        }
    }

    public void find(int id){
        int empLinkListNO = hashTab(id);
        Emp findEmp = empLinkListArr[empLinkListNO].find(id);
        if (findEmp==null){
            System.out.printf("哈希表中没有找到id为%d的雇员",id);
        }else {
            System.out.printf("id为%d的员工在%d条链表中,[id=%d,name=%s]",findEmp.id,empLinkListNO,findEmp.id,findEmp.name);
        }
        System.out.println();
    }
    /**
     * 这里使用取模的方法来获取数组的下标，使用哪个链表
     * @param id
     * @return
     */
    public int hashTab(int id) {
        return id % size;
    }

}