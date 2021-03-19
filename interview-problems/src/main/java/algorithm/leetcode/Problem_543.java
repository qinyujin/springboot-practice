package algorithm.leetcode;

import algorithm.offer.TreeNode;

/**
 * @author :覃玉锦
 * @create :2021-03-13 10:24:00
 * 二叉树的直径
 * https://leetcode-cn.com/problems/diameter-of-binary-tree/
 */
public class Problem_543 {
    public static void main(String[] args) {
        Problem_543 p = new Problem_543();
        TreeNode root = new TreeNode(1);
        TreeNode r1 = new TreeNode(2);
        root.left = r1;
        root.right = new TreeNode(3);
        r1.left = new TreeNode(4);
        r1.right = new TreeNode(5);
        int i = p.diameterOfBinaryTree(root);
        System.out.println(i);
    }

    //思路：通过求该节点左子节点高度和右子节点高度来相加就可以得到通过该节点的直径数。过程中使用max保存最大值
    private int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        int depth = depth(root);
        return max;
    }

    public int depth(TreeNode node){
        if(node==null)return 0;
        int l,r;
        l = depth(node.left);
        r = depth(node.right);
        //记录最大值
        max = Math.max(max,l+r);
        return Math.max(l,r)+1;
    }
}
