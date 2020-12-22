package dataStructure.avltree;

/**
 * @author :覃玉锦
 * @create :2020-12-15 14:17:00
 * 平衡二叉树及其旋转。平衡二叉树是二叉排序树的改进版本，树中每一个结点的左右子树高度差不会超过1
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
//        int[] arr = {4,3,6,5,7,8};
//        int[] arr = {10,12,8,9,7,6};
        int[] arr = {10,11,7,6,8,9};
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        System.out.println("中序遍历：");
        avlTree.infixOrder();
        System.out.println("左子树高度："+avlTree.leftHeight());
        System.out.println("右子树高度："+avlTree.rightHeight());
    }
}

class AVLTree{
    Node root;

    public int rightHeight(){
        if(root == null){
            return 0;
        }
        else {
            return root.rightHeight();
        }
    }

    public int leftHeight(){
        if(root == null){
            return 0;
        }
        else {
            return root.leftHeight();
        }
    }

    public int height(){
        if(root ==null){
            return 0;
        }
        else {
            return root.height();
        }
    }

    public void infixOrder(){
        if(root == null){
            System.out.println("树为空！");
        }
        else {
            root.infixOrder();
        }
    }

    public void add(Node node){
        if(root ==null){
            root = node;
        }
        else {
            root.add(node);
        }
    }
}

class Node {
    int value;
    Node left;
    Node right;

    /**
     * 右旋
     */
    public void rightRotate(){
        //创建newNode，值为当前结点值
        Node newNode = new Node(value);
        //把新节点的右子树设置为当前结点的右子树
        newNode.right = right;
        //把新节点左子树设置为当前节点左子树的右子树
        newNode.left = left.right;
        //把当前节点的值换为右子节点的值
        value = left.value;
        //把当前结点左子树设置为新节点
        right = newNode;
        //把当前结点的左子树设置成左子树的左子树
        left = left.left;
    }

    /**
     * 左旋，结合图示来写
     */
    public void leftRotate(){
        //创建newNode，值为当前结点值
        Node newNode = new Node(value);
        //把新节点的左子树设置为当前结点的左子树
        newNode.left = left;
        //把新节点右子树设置为当前节点右子树的左子树
        newNode.right = right.left;
        //把当前节点的值换为右子节点的值
        value = right.value;
        //把当前结点的右子树设置成右子树的右子树
        right = right.right;
        //把当前结点左子树设置为新节点
        left = newNode;
    }

    public int rightHeight(){
        if(right==null){
            return 0;
        }
        else{
            return right.height();
        }
    }

    /**
     * 求左子树高度
     * @return
     */
    public int leftHeight(){
        if(left==null){
            return 0;
        }
        else{
            return left.height();
        }
    }

    /**
     * 求出整棵树的高度
     * @return
     */
    public int height(){
        return Math.max((this.left==null?0:this.left.height()),(this.right==null?0:this.right.height()))+1;
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
        //添加完结点后判断是否需要左/右旋，通过高度差来判断
        //右子树比左子树高，选择左旋
        if(rightHeight() - leftHeight() > 1){
            if(right.leftHeight() > right.rightHeight()){
                right.rightRotate();
            }
            leftRotate();
        }
        //右旋
        else if(leftHeight() - rightHeight() > 1){
            //双旋转：对于右旋来说，如果当前节点的左子树（它）的右子树高度大于它的左子树，需要先对它进行左旋（把它右子树旋过来）
            if(left.rightHeight() > left.leftHeight()){
                left.leftRotate();
            }
            rightRotate();
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