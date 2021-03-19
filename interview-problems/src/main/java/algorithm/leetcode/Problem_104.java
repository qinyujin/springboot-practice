package algorithm.leetcode;

import algorithm.offer.TreeNode;

/**
 * @author :覃玉锦
 * @create :2021-03-12 12:06:00
 * 二叉树的最大深度
 * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 */
public class Problem_104 {
    public static void main(String[] args) {

    }

    //思路:如果节点是null返回0，否则返回1+左右子树中最大值。
    public int maxDepth(TreeNode root) {
        return root==null ? 0 : 1+ Math.max(maxDepth(root.left),maxDepth(root.right));
    }
}
