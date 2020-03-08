package com.ds.avl;

/**
 * 平衡二叉树
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        int[] arr = {10, 11, 7, 6, 8, 9};
//        int arr[] = {4, 3, 6, 5, 7, 8};
//        int arr[] = {10, 12, 8, 9, 7, 6};
        AVLTree avlTree = new AVLTree();
        for (int item : arr) {
            avlTree.add(new Node(item));
        }
        System.out.println("当前节点的高度：" + avlTree.height(avlTree.root));
        System.out.println("当前节点的左子节点的高度:" + avlTree.leftHeight(avlTree.root));
        System.out.println("当前节点的右子节点的高度：" + avlTree.rightHeight(avlTree.root));
//        avlTree.rightRotate();
//        System.out.println(avlTree.root);
//        System.out.println("当前节点的高度：" + avlTree.height(avlTree.root));
//        System.out.println("当前节点的左子节点的高度:" + avlTree.leftHeight(avlTree.root));
//        System.out.println("当前节点的右子节点的高度：" + avlTree.rightHeight(avlTree.root));
    }
}

class AVLTree {
    public Node root;

    public int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height();
    }

    public int leftHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.leftHeight();
    }

    public int rightHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.rightHeight();
    }

    public void rightRotate() {
        if (root == null) return;
        root.rightRotate();
    }

    public void leftRatate() {
        if (root == null) return;
        root.leftRotate();
    }

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
            } else {
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

    //获取左子树的高度
    public int leftHeight() {
        return left == null ? 0 : left.height();
    }

    //获取右子树的高度
    public int rightHeight() {
        return right == null ? 0 : right.height();
    }

    //以当前节点为根节点的树的高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    //左旋转
    public void leftRotate() {
        Node node = this;
        if (node == null) {
            return;
        }
        //创建一个新的节点
        Node newNode = new Node(node.value);
        //新节点的左子节点指向当前节点的左子节点
        newNode.left = node.left;
        //新节点的右子节点指向当前节点的 右子节点的左子节点
        newNode.right = node.right.left;
        //当前节点的左子节点指向新的节点
        node.left = newNode;
        //将当前节点的右子节点值赋给当前节点
        node.value = node.right.value;
        //当前节点指向右子节点的右子节点， 这时候 之前的nodd.right这个节点失去引用被垃圾回收
        node.right = node.right.right;
    }

    //右旋转
    public void rightRotate() {
        Node node = this;
        if (node == null) {
            return;
        }
        //创建一个新的节点
        Node newNode = new Node(node.value);
        newNode.right = node.right;
        newNode.left = node.left.right;
        node.right = newNode;
        node.value = node.left.value;
        node.left = node.left.left;
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
        //如果加完之后不是平衡二叉树了需要调整一下
        int leftHeight = leftHeight();
        int rightHeight = rightHeight();
        if (leftHeight - rightHeight > 1) {
            if (this.left != null && this.left.rightHeight() > this.left.leftHeight()) {
                left.leftRotate();
            }
            rightRotate();
        } else if (rightHeight - leftHeight > 1) {
            if (this.right != null && this.right.leftHeight() > this.right.rightHeight()) {
                right.rightRotate();
            }
            leftRotate();
        }
//        // 当添加完一个结点后，如果: ( 右子树的高度- 左子树的高度) > 1 ,  左旋转
//        if (rightHeight() - leftHeight() > 1) {
//            // 如果它的右子树的左子树的高度大于它的右子树的右子树的高度
//            if (right != null && right.leftHeight() > right.rightHeight()) {
//                // 先对右子结点进行右旋转
//                right.rightRotate();
//                // 然后在对当前结点进行左旋转
//                leftRotate(); // 左旋转..
//            } else {
//                // 直接进行左旋转即可
//                leftRotate();
//            }
//            return; // 必须要!!!
//        }
//        //如果 当添加完一个结点后，如果 (度 左子树的高度 -  右子树的高度) > 1,  右旋转
//        if (leftHeight() - rightHeight() > 1) {
//            // 如果它的左子树的右子树高度大于它的左子树的高度
//            if (left != null && left.rightHeight() > left.leftHeight()) {
//                // 先对当前结点的左结点( 左子树)-> 左旋转
//                left.leftRotate();
//                // 再对当前结点进行右旋转
//                rightRotate();
//            } else {
//                // 直接进行右旋转即可
//                rightRotate();
//            }
//        }
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