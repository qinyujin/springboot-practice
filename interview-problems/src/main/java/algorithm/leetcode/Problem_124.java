package algorithm.leetcode;

import algorithm.offer.TreeNode;

/**
 * @author :覃玉锦
 * @create :2021-03-19 21:30:00
 * 二叉树中的最大路径和
 * https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/
 */
public class Problem_124 {
    public static void main(String[] args) {
        Problem_124 p = new Problem_124();
        TreeNode r_10 = new TreeNode(-10);
        TreeNode r20 = new TreeNode(20);
        r_10.right = r20;
        r_10.left = new TreeNode(9);
        r20.left = new TreeNode(15);
        r20.right = new TreeNode(7);
        int i = p.maxPathSum(r_10);
        System.out.println(i);
    }

    private int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if(root==null)return 0;
        dfs(root);
        return max;
    }

    public int dfs(TreeNode root){
        if(root==null)return 0;
        //如果是负数，不取
        int leftMax = Math.max(0,dfs(root.left));
        int rightMax = Math.max(0,dfs(root.right));
        //左+当前+右
        max = Math.max(max,root.val+leftMax+rightMax);
        return root.val + Math.max(leftMax,rightMax);
    }
}
