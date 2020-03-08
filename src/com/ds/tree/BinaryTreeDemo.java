package com.ds.tree;

/**
 * 二叉树
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        HeroTreeNode node1 = new HeroTreeNode(1, "宋江");
        HeroTreeNode node2 = new HeroTreeNode(2, "吴用");
        HeroTreeNode node3 = new HeroTreeNode(3, "卢俊义");
        HeroTreeNode node4 = new HeroTreeNode(4, "公孙胜");
        HeroTreeNode node5 = new HeroTreeNode(5, "关胜");
        node1.left = node2;
        node1.right = node3;
        node3.right = node4;
        node3.left = node5;
        binaryTree.root = node1;

        //前序遍历
        System.out.println("前序遍历");
        binaryTree.preOrder();// 1,2,3,4  -- 1,2,3,5,4

        //中序遍历
        //System.out.println("中序遍历");
        //binaryTree.infixOrder();//2,1,3,4 --2,1,5,3,4

        //后序遍历
        //System.out.println("后序遍历");
        //binaryTree.postOrder();//2,4,3,1  --2,5,4,3,1

        //int findNo = 7;
        //前序查找
        //System.out.println("前序查找");
        //HeroTreeNode preOrderSearch = binaryTree.preOrderSearch(findNo);
        //if (preOrderSearch == null) {
        //    System.out.printf("没有找到编号为%d的节点", findNo);
        //} else {
        //    System.out.printf("查找到编号为%d的节点[no=%d, name=%s]\t", findNo, preOrderSearch.no, preOrderSearch.name);
        //}
        //System.out.println();

        //中序查找
        //System.out.println("中序查找");
        //HeroTreeNode infixOrederSearch = binaryTree.infixOrderSearch(findNo);
        //if (infixOrederSearch == null) {
        //    System.out.printf("没有找到编号为%d的节点", findNo);
        //} else {
        //   System.out.printf("查找到编号为%d的节点[no=%d,name=%s]", findNo, preOrderSearch.no, preOrderSearch.name);
        //}
        //System.out.println();
        //后序查找
        //System.out.println("后序查找");
        //HeroTreeNode postOrderSearch = binaryTree.postOrderSearch(findNo);
        //if (postOrderSearch==null){
        //    System.out.printf("没有找到编号为%d的节点",findNo);
        //}else {
        //    System.out.printf("找到编号为%d的节点[no=%d,name=%s]",findNo,postOrderSearch.no,postOrderSearch.name);
        //}
//        binaryTree.delNode(5);//删除5号节点之后
        binaryTree.delNode2(3);
        System.out.println("打印前序遍历");
        binaryTree.preOrder();
    }
}


class BinaryTree {
    public HeroTreeNode root;

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
            }else {
                root.delNode2(delNo);
            }
        }else {
            System.out.println("二叉树为空，不能删除");
        }
    }

}

class HeroTreeNode {
    public int no;
    public String name;
    public HeroTreeNode left;//默认为null
    public HeroTreeNode right;//默认为null

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
