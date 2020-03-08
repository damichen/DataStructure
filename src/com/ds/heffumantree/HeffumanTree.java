package com.ds.heffumantree;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * 赫夫曼树
 */
public class HeffumanTree {
    public static void main(String[] args) {
        int arr[] = {13, 7, 8, 3, 29, 6, 1};
        Node root = createHeffumanTree(arr);
        preOrder(root);
    }

    public static Node createHeffumanTree(int arr[]) {
        if (arr == null) {
            return null;
        }
        List<Node> nodes = new LinkedList<>();
        for (int val : arr) {
            nodes.add(new Node(val));
        }
        while (nodes.size() > 1) {
            // 这里也可以让Node实现 Comparable return this.value -o.value; 就代表是升序
            Collections.sort(nodes, new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    //这样减是升序 ， -（o1.value - o2.value）是降序
                    return o1.value - o2.value;
                }
            });
            System.out.println("排序后" + nodes);


            //取出根节点权值最小的两颗二叉树
            //(1) 取出权值最小的结点（二叉树）
            Node leftNode = nodes.get(0);
            //(2) 取出权值第二小的结点（二叉树）
            Node rightNode = nodes.get(1);
            //(3)构建一颗新的二叉树

            //将已经使用过的节点删除掉
            nodes.remove(1);
            nodes.remove(0);

            Node root = new Node(leftNode.value + rightNode.value);
            root.left = leftNode;
            root.right = rightNode;
            nodes.add(root);
        }

        return nodes.get(0);
    }
    public static void preOrder(Node root){
        if (root ==null){
            System.out.println("树空，不能遍历");
        }else {
            root.preOreder(root);
        }
    }
}

class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int value) {
        this.value = value;
    }

    //前序遍历
    public void preOreder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node);
        preOreder(node.left);
        preOreder(node.right);
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
