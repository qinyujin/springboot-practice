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
        if (root == null) return 0;
        dfs(root);
        return max;
    }

    //假设现在的子树为[a,b,c] b是左子节点，那么有3种情况(路径只能走一次，不能折返):
    //1、b-a-c                                   这条路径不需要往上递归
    //2、b-a-a.super(a的父节点) 或者 c-a-a.super   需要通过递归给到父节点
    public int dfs(TreeNode root) {
        if (root == null) return 0;
        //如果左右子树最大路径是负数则不添加到路径中
        int leftMax = Math.max(0, dfs(root.left));
        int rightMax = Math.max(0, dfs(root.right));
        //情况1有可能出现最大长度
        max = Math.max(max, root.val + leftMax + rightMax);
        //根据分析的情况2、3，这里是单边长度
        return root.val + Math.max(leftMax, rightMax);
    }
}
