package dataStructure.binarysorttree;

/**
 * @author :覃玉锦
 * @create :2020-12-14 19:13:00
 * 二叉排序树，任一节点左子节点小，右子节点大（相等随意）
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree tree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            tree.add(new Node(arr[i]));
        }
        System.out.println("中序遍历");
        tree.infixOrder();
        tree.delNode(2);
        tree.delNode(3);
        tree.delNode(10);
        tree.delNode(1);
        tree.delNode(5);
        tree.delNode(9);
        tree.delNode(12);
        tree.delNode(7);
        System.out.println("删除后的中序：");
        tree.infixOrder();
    }
}

class BinarySortTree {
    Node root;

    public Node getRoot() {
        return root;
    }

    /**
     * 根据值删除节点，有三种情况的删除节点：1、叶子节点。2、只有一个子树的节点。3、有两个子树的节点
     *
     * @param value 节点的值
     */
    public void delNode(int value) {
        //需要删除的目标节点
        Node targetNode = search(value);
        if (targetNode == null) {
            return;
        }
        //只有一个节点
        if (root.left == null && root.right == null) {
            root = null;
            return;
        }
        Node parentNode = searchParent(value);
        //1、删除叶子节点，父节点直接删掉
        if (targetNode.left == null && targetNode.right == null) {
            //判断是左子还是右子
            if (parentNode.left != null && parentNode.left.value == value) {
                parentNode.left = null;
            } else if (parentNode.right != null && parentNode.right.value == value) {
                parentNode.right = null;
            }
        }
        //2、两个子树
        else if (targetNode.left != null && targetNode.right != null) {
            int minVal = delRightTreeMin(targetNode.right);
            targetNode.value = minVal;
        }
        //3、只有一个子树，有四种情况：1：1.1-target有左子树,并且它是父节点的左子树 1.2-它是父节点的右子树 2：2.1-target有右子树,并且它是父节点的左子树 2.2-它是父节点的右子树
        else {
            //1
            if(targetNode.left!=null){
                //1.1
                if (parentNode!=null) {
                    if(parentNode.left.value == targetNode.value){
                        parentNode.left = targetNode.left;
                    }
                    //1.2
                    else {
                        parentNode.right = targetNode.left;
                    }
                } else {
                    root = targetNode.left;
                }
            }
            //2
            else {
                //2.1
                if (parentNode!=null) {
                    if(parentNode.left.value == targetNode.value){
                        parentNode.left = targetNode.right;
                    }
                    //2.2
                    else {
                        parentNode.right = targetNode.right;
                    }
                } else {
                    root = targetNode.right;
                }
            }
        }
    }

    /**
     * 删除右子树中的最小的节点，并且把这个值返回
     *
     * @param node 有两个子树的节点，这里当作根节点处理
     * @return 子树中最小的值
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        while (target.left != null) {
            target = target.left;
        }
        delNode(target.value);
        return target.value;
    }

    public Node searchParent(int value) {
        if (root == null) {
            System.out.println("数为空");
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    public Node search(int value) {
        if (root == null) {
            System.out.println("当前树为空");
            return null;
        } else {
            return root.search(value);
        }
    }

    public void infixOrder() {
        if (root == null) {
            System.out.println("根节点为空！");
        } else {
            root.infixOrder();
        }
    }

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }
}

class Node {
    int value;

    Node left;

    Node right;

    /**
     * 查找指定的父节点
     *
     * @param value 节点的值
     * @return 节点的父节点
     */
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        }
        //小于往左，注意这里不能直接向左或者向右递归，因为回不来了
        else if (value < this.value) {
            if (this.left != null) {
                return this.left.searchParent(value);
            }
        } else if (value >= this.value) {
            if (this.right != null) {
                return this.right.searchParent(value);
            }
        }
        return null;
    }

    /**
     * 查找指定结点
     *
     * @param value 节点的值
     * @return Node类型
     */
    public Node search(int value) {
        if (this.value == value) {
            return this;
        } else {
            if (this.left != null && value < this.value) {
                return this.left.search(value);
            }
            if (this.right != null && value > this.value) {
                return this.right.search(value);
            }
        }
        return null;
    }

    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        if (this != null) {
            System.out.println(this);
        }
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    public void add(Node node) {
        if (node.value < this.value) {
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

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}