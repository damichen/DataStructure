package com.ds.tree.threadedbinarytree;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.List;

/**
 * 线索化二叉树
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        //测试一把中序线索二叉树的功能
        HeroTreeNode root = new HeroTreeNode(1, "tom");
        HeroTreeNode node2 = new HeroTreeNode(3, "jack");
        HeroTreeNode node3 = new HeroTreeNode(6, "smith");
        HeroTreeNode node4 = new HeroTreeNode(8, "mary");
        HeroTreeNode node5 = new HeroTreeNode(10, "king");
        HeroTreeNode node6 = new HeroTreeNode(14, "dim");

        //二叉树，后面我们要递归创建, 现在简单处理使用手动创建
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.root = root;
        //threadedBinaryTree.threadedNodes();
//        threadedBinaryTree.threadedNodesPre();
        threadedBinaryTree.threadedNodesPost();
        //测试: 以 10 号节点测试
        HeroTreeNode leftNode = node5.left;
        HeroTreeNode rightNode = node5.right;
        System.out.println("10 号结点的前驱结点是 =" + leftNode); //3
        System.out.println("10 号结点的后继结点是=" + rightNode); //1

        //threadedBinaryTree.threadList();//8,3,10,1,14,6
        //threadedBinaryTree.threadedListPre();//1,3,8,10,6,14
    }
}

class ThreadedBinaryTree {
    public HeroTreeNode root;

    private HeroTreeNode pre;

    //这边有一个特性，就是叶子节点的后继节点会和所有飞叶子节点一一对应
    public void threadList() {
        if (root == null) {
            System.out.println("二叉树为空");
            return;
        }
        HeroTreeNode node = root;
        while (node != null) {
            //循环的找到 leftType == 1 的结点，第一个找到就是 8 结点
            //后面随着遍历而变化,因为当 leftType==1 时，说明该结点是按照线索化
            //处理后的有效结点
            while (node.leftType == 0) {
                node = node.left;
            }
            //打印当前节点
            System.out.println(node);
            //这个时候应该找后继节点,打印
            while (node.rightType == 1 && node.right != null) {
                node = node.right;
                System.out.println(node);
            }
            //指向下一个节点
            node = node.right;
        }
    }

    //前序线索二叉树的遍历--线性遍历
    public void threadedListPre() {
        this.threadedListPre(root);
    }

    public void threadedListPre(HeroTreeNode node) {
        if (node == null) {
            System.out.println("空二叉树");
            return;
        }
        while (node != null) {
            //这里直接将不是
            while (node.leftType != 1) {
                System.out.println(node);
                node = node.left;
            }
            //leftType ==1 的代表是叶子节点，在最下边直接打印就行了
            System.out.println(node);
            //利用后继节点指向下一个节点
            node = node.right;
        }
    }

    //后序遍历 暂时没有想出来方法
    // 从最左边叶子节点开始遍历(根据线索的) 8->10(left =8; right =3)-3(left =8,right =10)遍历不下去了
    public void threadedListPost() {
        HeroTreeNode node =root;
        if (node == null) {
            System.out.println("空二叉树");
            return;
        }
        while (node != null) {
            while (node.leftType!=1){
                node = node.left;
            }
            System.out.println(node);
            while (node.rightType!=1){

            }
            node = node.right;
        }
    }

    public void threadedNodes() {
        this.threadedNodes(root);
    }

    //编写对二叉树进行中序线索化的方法
    public void threadedNodes(HeroTreeNode node) {
        //空的就说明（不能线索化）终止某一个方向递归
        if (node == null) {
            return;
        }
        //向左递归 -线索化左子节点
        threadedNodes(node.left);
        //线索化当前节点
        if (node.left == null) {//这边判断没有左子节点的时候才能线索化，前驱节点
            node.left = pre;
            node.leftType = 1;
        }
        //这里需要注意的是此时并不知道下一个节点是哪个，即后继节点暂时不知道是哪个节点，
        //所以在下一个节点的时候确定 并设置值
        if (pre != null && pre.right == null) {
            pre.right = node;
            pre.rightType = 1;
        }
        //每处理一个结点后，让当前结点是下一个结点的前驱结点
        pre = node;
        //线索化右子节点
        threadedNodes(node.right);
    }

    public void threadedNodesPre() {
        this.threadedNodesPre(root);
    }

    //前序线索化
    public void threadedNodesPre(HeroTreeNode node) {
        if (node == null) {
            return;
        }
        //如果是叶子节点，线索化当前节点
        if (node.left == null) {
            node.left = pre;
            node.leftType = 1;
        }
        //只能利用当前节点的前一个节点来，确定当前节点就是前一个节点的后继节点
        if (pre != null && pre.right == null) {
            pre.right = node;
            pre.rightType = 1;
        }
        //赋值，用来在下一个的时候线索化后继节点
        pre = node;
        //直接向左线索化,如果此节点==1了说明已经被线索化再递归会死循环
        if (node.leftType != 1) {
            threadedNodesPre(node.left);
        }
        //向右线索化
        if (node.rightType != 1) {
            threadedNodesPre(node.right);
        }
    }

    //后序线索化二叉树
    public void threadedNodesPost() {
        this.threadedNodesPost(root);
    }

    public void threadedNodesPost(HeroTreeNode node) {
        if (node == null) {
            return;
        }
        //左递归线索化
        if (node.leftType != 1)
            threadedNodesPost(node.left);
        //向右线索化
        if (node.rightType != 1)
            threadedNodesPost(node.right);
        //当前节点
        if (node.left == null) {
            node.left = pre;
            node.leftType = 1;
        }
        if (pre != null && pre.right == null) {
            pre.right = node;
            pre.rightType = 1;
        }
        pre = node;
    }

    public void preOrder() {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("二叉树为空~~");
        }
    }

    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉树为空~");
        }
    }

    public void postOrder() {
        if (root != null) {
            root.postOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    public HeroTreeNode preOrderSearch(int findNo) {
        if (root == null) {
            System.out.println("二叉树为空");
            return null;
        }
        return root.preOrderSearch(findNo);
    }

    public HeroTreeNode infixOrderSearch(int findNo) {
        if (root == null) {
            System.out.println("二叉树为空");
            return null;
        }
        return root.infixOrderSearch(findNo);
    }

    public HeroTreeNode postOrderSearch(int findNo) {
        if (root == null) {
            System.out.println("二叉树为空");
            return null;
        }
        return root.postOrederSearch(findNo);
    }

    public void delNode(int delNo) {
        if (root != null) {
            if (root.no == delNo) {
                root = null;
            } else {
                //执行递归删除
                root.delNode(delNo);
            }
        } else {
            System.out.println("二叉树为空，不能删除");
        }
    }

    public void delNode2(int delNo) {
        if (root != null) {
            if (root.no == delNo) {
                if (root.left != null) {
                    root = root.left;
                } else if (root.right != null) {
                    root = root.right;
                } else {
                    root = null;
                }
            } else {
                root.delNode2(delNo);
            }
        } else {
            System.out.println("二叉树为空，不能删除");
        }
    }

}


class HeroTreeNode {
    public int no;
    public String name;
    public HeroTreeNode left;//默认为null
    public HeroTreeNode right;//默认为null

    //说明
    //1. 如果 leftType == 0 表示指向的是左子树, 如果 1 则表示指向前驱结点
    //2. 如果 rightType == 0 表示指向是右子树, 如果 1 表示指向后继结点
    public int leftType;//成员变量默认是0
    public int rightType;

    public HeroTreeNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroTreeNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 编写前序遍历方法:
     * 父节点-》左节点-》右节点
     */
    public void preOrder() {
        System.out.println(this);//这里是输出此节点
        if (this.left != null) {//如果有左节点，递归进行前序遍历
            this.left.preOrder();
        }
        if (this.right != null) {//递归进行前序遍历
            this.right.preOrder();
        }
    }

    /**
     * 中序遍历：
     * 左节点-》父节点-》右节点
     */
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * 后续遍历：
     * 左节点-》右节点-》父节点
     */
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    /**
     * 前序查找:
     * 找到了就返回，没有找到就先判断有没有左子节点，有就进行左递归
     * 没有就判断有没有右节点，有就进行有递归查找，找到就返回这个值，找不到
     * 发挥null
     */
    public HeroTreeNode preOrderSearch(int findNo) {
//        System.out.printf("前序查找执行\t");
        if (this.no == findNo) {
            return this;
        }
        HeroTreeNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(findNo);//这里需要注意,不能直接return回去，如果是null还要让它自动去右边找
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.preOrderSearch(findNo);
        }
        if (resNode != null) {
            return resNode;
        }
        return null;
    }

    /**
     * 中序查找；
     * 先从左节点开始查找，如果不为空就做递归，为空就判断当前值是否为我们查找的，判断右节点是否为空
     * 不为空就进行右递归，为空就返回
     *
     * @param findNo 查找的编号
     * @return
     */
    public HeroTreeNode infixOrderSearch(int findNo) {
//        System.out.printf("中序查找执行\t");
        HeroTreeNode findNode = null;
        if (this.left != null) {
            findNode = this.left.infixOrderSearch(findNo);//此处也不能返回，没有值的时候得让它继续查找其它值
        }
        if (findNode != null) {
            return findNode;
        }
        if (this.no == findNo) {
            return this;
        }
        if (this.right != null) {
            findNode = this.right.infixOrderSearch(findNo);
        }
        if (findNode != null) {
            return findNode;
        }
        return null;
    }

    public HeroTreeNode postOrederSearch(int findNo) {
//        System.out.printf("后序查找执行\t");
        HeroTreeNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrederSearch(findNo);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.postOrederSearch(findNo);
        }
        if (resNode != null) {
            return resNode;
        }
        if (findNo == this.no) {
            return this;
        }
        return null;
    }

    /**
     * 1.因为我们的二叉树是单向的，所以判断当前节点的子节点是否要删除的，而不能直接判断当前节点；
     * 2.如果当前节点的左节点不是空，而且this.left.no == delNo ,则此节点就是我们要删除的节点,
     * this.left=null,return;（结束递归，并删除）
     * 3.如果当前节点的右节点不是空，而且this.right.no == delNo,结束递归并删除此节点；
     * 4。如果第2,3步还没有找到要删除的节点，就向左子树进行递归
     * 5.如果第4步还没找到删除的节点，就向右子树进行递归
     * <p>
     * 这边主要是这边是无序的二叉树，所以可以在删除有子节点的时候让你直接把下边的子节点都删掉
     *
     * @param delNo
     */
    public void delNode(int delNo) {
        if (this.left != null && this.left.no == delNo) {//代表找到，删除并返回
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == delNo) {
            this.right = null;
            return;
        }
        //这一步执行完毕不能return,否则没找到就不会到右子树去递归了
        if (this.left != null) {
            this.left.delNode(delNo);
        }
        if (this.right != null) {
            this.right.delNode(delNo);
        }

    }

    public void delNode2(int delNo) {
        if (this.left != null && this.left.no == delNo) {
            if (this.left.left != null) {
                this.left = this.left.left;
            } else if (this.left.right != null) {
                this.left = this.left.right;
            } else {
                this.left = null;
            }
            return;
        }
        if (this.right != null && this.right.no == delNo) {
            if (this.right.left != null) {
                this.right = this.right.left;
            } else if (this.right.right != null) {
                this.right = this.right.right;
            } else {
                this.right = null;
            }
            return;
        }
        if (this.left != null) {
            this.left.delNode2(delNo);
        }
        if (this.right != null) {
            this.right.delNode2(delNo);
        }
    }
}
