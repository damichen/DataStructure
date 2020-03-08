package com.ds.tree;

/**
 * 顺序二叉树
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        // 要求:  这种注意的是需要完全二叉树，并且有序的数组
        //1) 右图的二叉树的结点，要求以数组的方式来存放 arr : [1, 2, 3, 4, 5, 6, 6]
        //2) 要求在遍历数组 arr 时，仍然可以以前序遍历，中序遍历和后序遍历的方式完成结点的遍历
        int arr[] = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
//        arrBinaryTree.preOreder();//1,2,4,5,3,6,7
//        arrBinaryTree.infixOrder();//4,2,5,1,6,3,7
        arrBinaryTree.postOrder();//4,5,2,6,7,3,1
    }
}


class ArrBinaryTree {
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    /**
     * 顺序存储二叉树的特点: （这里的n是指数组的下标，与数组arr【n】的值没有关系）
     * 1) 顺序二叉树通常只考虑完全二叉树
     * 2) 第 n 个元素的左子节点为 2 * n + 1
     * 3) 第 n 个元素的右子节点为 2 * n + 2
     * 4) 第 n 个元素的父节点为 (n-1) / 2
     * 5) n : 表示二叉树中的第几个元素(按 0 开始编号)
     */
    /**
     * 前序遍历
     *
     * @param
     */
    public void preOreder() {
        this.preOrder(0);//前序遍历默认从数组为0的位置开始
    }

    public void preOrder(int index) {
        if (arr == null) {
            System.out.println("数组为空不能遍历");
            return;
        }
        System.out.println(arr[index]);
        //这里判断左子节点是否在数组中存在，即有没有越界
        if ((2 * index + 1) < arr.length) {
            preOrder(2 * index + 1);//左子节点递归
        }
        if ((2 * index + 2) < arr.length) {
            preOrder(2 * index + 2);
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        this.infixOrder(0);
    }

    public void infixOrder(int index) {
        if (arr == null) {
            System.out.println("空数组不能遍历");
            return;
        }
        //这里判断左子节点是否在数组中存在，即有没有越界
        if ((2 * index + 1) < arr.length) {
            infixOrder(2 * index + 1);//左子节点递归
        }
        System.out.println(arr[index]);
        if ((2 * index + 2) < arr.length) {
            infixOrder(2 * index + 2);
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        this.postOrder(0);
    }

    public void postOrder(int index) {
        if (arr == null) {
            System.out.println("数组为空不能遍历");
            return;
        }
        //这里判断左子节点是否在数组中存在，即有没有越界
        if ((2 * index + 1) < arr.length) {
            postOrder(2 * index + 1);//左子节点递归
        }
        if ((2 * index + 2) < arr.length) {
            postOrder(2 * index + 2);
        }
        System.out.println(arr[index]);
    }

}