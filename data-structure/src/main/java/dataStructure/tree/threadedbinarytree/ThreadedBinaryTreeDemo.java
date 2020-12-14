package dataStructure.tree.threadedbinarytree;

import lombok.Data;

/**
 * @author :覃玉锦
 * @create :2020-12-10 10:28:00
 * 线索化二叉树，根据前序、中序、后序区分有三种线索化
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1,"tom");
        HeroNode node2 = new HeroNode(3,"jack");
        HeroNode node3 = new HeroNode(6,"smith");
        HeroNode node4 = new HeroNode(8,"mary");
        HeroNode node5 = new HeroNode(10,"king");
        HeroNode node6 = new HeroNode(14,"dim");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);

        threadedBinaryTree.threadedInfixBinaryTree();
//        threadedBinaryTree.threadedPostBinaryTree();

        threadedBinaryTree.threadedList();
    }
}

class ThreadedBinaryTree {
    private HeroNode root;
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void threadedInfixBinaryTree(){
        this.threadedInfixBinaryTree(root);
    }

    public void threadedPostBinaryTree(){
        this.threadedPostBinaryTree(root);
    }

    /**
     * 中序线索化
     */
    public void threadedInfixBinaryTree(HeroNode node){
        if(node == null){
            return;
        }
        //中序，先左，后中，再右
        //有左子树，递归
        if(node.getLeft() != null){
            threadedInfixBinaryTree(node.getLeft());
        }
        //对于线索化的处理：如果左指针为空，则指向前缀
        if(node.getLeft()==null){
            node.setLeft(pre);
            node.setLeftType(1);
        }
        //对于后缀的处理：由于只有node和pre两个节点所以后缀得在pre判断，需注意pre是否为空
        if(pre!=null && pre.getRight()==null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        //向后移动
        pre = node;

        if(node.getRight()!=null){
            threadedInfixBinaryTree(node.getRight());
        }
    }

    /**
     * 后序线索化
     */
    public void threadedPostBinaryTree(HeroNode node){
        if(node==null){
            return;
        }
        if(node.getLeft()!=null){
            threadedPostBinaryTree(node.getLeft());
        }
        if(node.getRight()!=null){
            threadedPostBinaryTree(node.getRight());
        }
        if(node.getLeft()==null){
            node.setLeft(pre);
            node.setLeftType(1);
        }
        //对于后缀的处理：由于只有node和pre两个节点所以后缀得在pre判断，需注意pre是否为空
        if(pre!=null && pre.getRight()==null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        //向后移动
        pre = node;
    }

    /**
     * 线索化中序遍历
     */
    public void threadedList(){
        HeroNode node = root;
        while (node!= null){
            //中序顺序为左-中-右
            //找到左
            while (node.getLeftType()!=1){
                node = node.getLeft();
            }
            System.out.print(node+" ");
            while (node.getRightType()==1){
                node = node.getRight();
                System.out.print(node+" ");
            }
            node = node.getRight();
        }
        System.out.println();
    }

    public void preOrder(){
        if(this.root!=null){
            System.out.println("前序遍历");
            this.root.preOrder();
        }
        else {
            System.out.println("根节点为空！");
        }
    }

    public void infixOrder(){
        System.out.println("中序遍历");
        if(this.root!=null){
            this.root.infixOrder();
        }
        else {
            System.out.println("根节点为空！");
        }
    }

    public void postOrder(){
        System.out.println("后续遍历");
        if(this.root!=null){
            this.root.postOrder();
        }
        else {
            System.out.println("根节点为空！");
        }
    }

    public HeroNode preOrderSearch(int no){
        if(this.root!=null){
            return this.root.preOrderSearch(no);
        }
        return null;
    }

    public HeroNode infixOrderSearch(int no){
        if(this.root!=null){
            return this.root.infixOrderSearch(no);
        }
        return null;
    }

    public HeroNode postOrderSearch(int no){
        if(this.root!=null){
            return this.root.postOrderSearch(no);
        }
        return null;
    }

    public void delNode(int no){
        if(root!=null){
            if(root.getNo()==no){
                root = null;
            }
            else {
                root.delNode(no);
            }
        }
        else {
            System.out.println("树为空！");
        }
    }
}


@Data
class HeroNode{
    private int no;
    private String name;
    //左子树
    private HeroNode left;
    //右子树
    private HeroNode right;
    //0：子树 1：前驱/后继
    private int leftType;
    private int rightType;

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", leftType=" + leftType +
                ", rightType=" + rightType +
                '}';
    }

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    /**
     * 前序遍历，顺序为根-》左-》右
     */
    public void preOrder(){
        System.out.println(this);
        //如果左子树不为空，递归输出左子树
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }
    }

    /**
     * 中序，左-》根-》右
     */
    public void infixOrder(){
        //如果左子树不为空，递归输出左子树
        if(this.left!=null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right!=null){
            this.right.infixOrder();
        }
    }

    /**
     * 后序，左-》右-》根
     */
    public void postOrder(){
        //如果左子树不为空，递归输出左子树
        if(this.left!=null){
            this.left.postOrder();
        }
        if(this.right!=null){
            this.right.postOrder();
        }
        System.out.println(this);
    }

    /**
     * 前序查找，根据id
     * @param no
     * @return
     */
    public HeroNode preOrderSearch(int no){
        if(this.no == no){
            return this;
        }
        HeroNode resNode = null;
        if(this.left!=null){
            resNode = this.left.preOrderSearch(no);
        }
        if(resNode!=null){
            return resNode;
        }
        if(this.right!=null){
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 中序查找
     * @param no
     * @return
     */
    public HeroNode infixOrderSearch(int no){
        HeroNode resNode = null;
        if(this.left!=null){
            resNode = this.left.infixOrderSearch(no);
        }
        if(resNode!=null){
            return resNode;
        }
        if(this.no == no){
            return this;
        }
        if(this.right!=null){
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 后序
     * @param no
     * @return
     */
    public HeroNode postOrderSearch(int no){
        HeroNode resNode = null;
        if(this.left!=null){
            resNode = this.left.postOrderSearch(no);
        }
        if(resNode!=null){
            return resNode;
        }
        if(this.right!=null){
            resNode = this.right.postOrderSearch(no);
        }
        if(resNode!=null){
            return resNode;
        }
        if(this.no == no){
            return this;
        }
        return resNode;
    }

    /**
     * 递归删除节点，如果是叶子节点，直接删除，如果是非叶子节点，把子树也删掉。这里没有考虑root节点，放在BinaryTree里考虑
     * @param no
     */
    public void delNode(int no){
        //如果是左子节点
        if(this.left!=null && this.left.no == no){
            this.left = null;
            return;
        }
        if(this.right!=null && this.right.no == no){
            this.right = null;
            return;
        }
        //如果都不是,递归
        if(this.left!=null){
            this.left.delNode(no);
        }
        if(this.right!=null){
            this.right.delNode(no);
        }
    }
}