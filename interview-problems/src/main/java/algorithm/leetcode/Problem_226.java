package algorithm.leetcode;

import algorithm.offer.TreeNode;

/**
 * @author :覃玉锦
 * @create :2021-03-12 19:52:00
 * 翻转二叉树
 * https://leetcode-cn.com/problems/invert-binary-tree/
 */
public class Problem_226 {
    public static void main(String[] args) {
        Problem_226 p = new Problem_226();
        TreeNode r1 = new TreeNode(4);
        TreeNode r2 = new TreeNode(2);
        TreeNode r3 = new TreeNode(7);
        r1.left =r2;r1.right = r3;
        r2.left = new TreeNode(1);
        r2.right = new TreeNode(3);
        r3.left = new TreeNode(6);
        r3.right = new TreeNode(9);
        TreeNode treeNode = p.invertTree(r1);
        System.out.println();
    }

    public TreeNode invertTree(TreeNode root) {
        if(root==null) return null;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
}
