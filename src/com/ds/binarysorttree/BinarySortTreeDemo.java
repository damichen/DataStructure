package com.ds.binarysorttree;

/**
 * 二叉排序树
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {

        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        //循环的添加结点到二叉排序树
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        System.out.println("中序遍历");
        binarySortTree.infixOrder();//1,3,5,7,9,10,12
        //测试一下删除叶子结点
        binarySortTree.delNode(12);
        binarySortTree.delNode(5);
        binarySortTree.delNode(10);
        binarySortTree.delNode(2);
        binarySortTree.delNode(3);
        binarySortTree.delNode(7);
//        binarySortTree.delNode(9);
//        binarySortTree.delNode(1);
        System.out.println("root=" + binarySortTree.root);
        System.out.println("删除结点后");
        binarySortTree.infixOrder();
    }
}

class BinarySortTree {
    public Node root;

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public void infixOrder() {
        if (root == null) {
            System.out.println("二叉树为空不能遍历");
            return;
        }
        root.infixOrder(root);
    }

    public Node search(int val) {
        if (root == null) {
            return null;
        }
        return root.search(val);
    }

    public Node searchParent(int val) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(val);
        }
    }

    //删除结点
    public void delNode(int value) {
        if (root == null) {
            return;
        }
        Node searchNode = root.search(value);
        Node searchParentNode = root.searchParent(value);
        if (searchNode == null) {//没有找到这个节点
            return;
        } else if (searchParentNode == null) {//代表就是根节点
            //根节点没有父节点，得这样算
            if (root.left != null && root.right != null) {
                int changeVal = delLeftTreeMax(searchNode);
                searchNode.value = changeVal;
            } else if (root.left != null) {
                root = root.left; //没有右边的情况下
            } else if (root.right != null) {
                root = root.right;
            }else {
                root = null;
            }
        } else if (searchNode.left == null && searchNode.right == null) {//叶子节点
            if (value > searchParentNode.value) {
                searchParentNode.right = null;
            } else {
                searchParentNode.left = null;
            }
        } else if (searchNode.left == null) {//被删除的节点有一个右节点
            if (value > searchParentNode.value) {
                searchParentNode.right = searchNode.right;
            } else {
                searchParentNode.left = searchNode.right;
            }
        } else if (searchNode.right == null) {//被删除的节点有一个左节点
            if (value > searchParentNode.value) {
                searchParentNode.right = searchNode.left;
            } else {
                searchParentNode.left = searchNode.left;
            }
        } else {//被删除的节点左节点和右节点都有
            int changeVal = delLeftTreeMax(searchNode);
            searchNode.value = changeVal;
        }
    }
    //编写方法:
    //1. 返回的 以 node 为根结点的二叉排序树的最小结点的值
    //2. 删除 node 为根结点的二叉排序树的最小结点

    /**
     * @param node 传入的结点(当做二叉排序树的根结点)
     * @return 返回的 以 node 为根结点的二叉排序树的左边的最大节点的值
     */
    public int delLeftTreeMax(Node node) {
        Node target = node.left;
        //使得节点指向子节点中最大节点
        while (target.right != null) {
            target = target.right;
        }
        //将最小节点删除
        delNode(target.value);
        return target.value;
    }
}

class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    /**
     * 按照二叉排序树的方式插入
     *
     * @param node 需要插入的节点
     */
    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (this.value > node.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }

    }

    public void infixOrder(Node node) {
        if (node.left != null) {
            infixOrder(node.left);
        }
        System.out.println(node);
        if (node.right != null) {
            infixOrder(node.right);
        }
    }

    /**
     * @param value 希望删除的结点的值
     * @return 如果找到返回该结点，否则返回 null
     */
    public Node search(int value) {
        if (this.value == value) {
            return this;
        } else if (value < this.value) {
            if (this.left != null) {
                return this.left.search(value);
            }
            return null;
        } else {
            if (this.right != null) {
                return this.right.search(value);
            }
            return null;
        }
    }
    //查找要删除结点的父结点

    /**
     * @param value 要找到的结点的值
     *              * @return 返回的是要删除的结点的父结点，如果没有就返回 null
     */
    public Node searchParent(int value) {
        if (this.value > value) {
            if (this.left != null) {
                if (this.left.value == value) {
                    return this;
                } else {
                    return this.left.searchParent(value);
                }
            }
            return null;
        } else if (this.value < value) {
            if (this.right != null) {
                if (this.right.value == value) {
                    return this;
                } else {
                    return this.right.searchParent(value);
                }
            }
        }
        return null;
    }


}