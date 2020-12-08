package dataStructure.tree;

import lombok.Data;

/**
 * @author :覃玉锦
 * @create :2020-12-08 16:59:00
 * 二叉树的前序、中序、后序遍历
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1,"宋江");
        HeroNode node2 = new HeroNode(2,"吴用");
        HeroNode node3 = new HeroNode(3,"卢俊义");
        HeroNode node4 = new HeroNode(4,"林冲");
        HeroNode node5 = new HeroNode(5,"关胜");

        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);

        binaryTree.setRoot(root);
//        binaryTree.preOrder();
//        binaryTree.infixOrder();
//        binaryTree.postOrder();

//        HeroNode node = binaryTree.preOrderSearch(5);
//        HeroNode node = binaryTree.infixOrderSearch(5);
//        HeroNode node = binaryTree.postOrderSearch(5);
//        System.out.println(node);

        System.out.println("删除前前序");
        binaryTree.preOrder();
        binaryTree.delNode(1);
        System.out.println("删除后前序");
        binaryTree.preOrder();
    }
}

class BinaryTree{
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
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

/**
 * 使用hero节点模拟一个树节点
 */
@Data
class HeroNode{
    private int no;
    private String name;
    //左子树
    private HeroNode left;
    //右子树
    private HeroNode right;

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
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