package algorithm.leetcode;

import algorithm.offer.TreeNode;

/**
 * @author :覃玉锦
 * @create :2021-03-13 10:12:00
 * 合并二叉树
 * https://leetcode-cn.com/problems/merge-two-binary-trees/
 */
public class Problem_617 {
    public static void main(String[] args) {
        Problem_617 p = new Problem_617();
        TreeNode roo1 = new TreeNode(1);
        TreeNode r2 = new TreeNode(3);
        roo1.left = r2;
        roo1.right = new TreeNode(2);
        r2.left = new TreeNode(5);

        TreeNode root2 = new TreeNode(2);
        TreeNode n2 = new TreeNode(1);
        TreeNode n3 = new TreeNode(3);
        root2.left = n2;root2.right = n3;
        n2.right = new TreeNode(4);
        n3.right = new TreeNode(7);

        TreeNode treeNode = p.mergeTrees(roo1, root2);
        System.out.println();
    }

    //思路：合并一个节点只有三种情况:
    //1、两个树都是空，那么合并结果就是空
    //2、只有一个空，那么合并结果就是非空的节点
    //3、两个都不为空，那么就求和
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        //这里把一种为空和两种为空的情况处理
        if(root1 == null)return root2;
        if(root2==null)return root1;
        //都不为空则求和
        TreeNode merged = new TreeNode(root1.val+root2.val);
        //两个树同时左移或者右移
        merged.left = mergeTrees(root1.left,root2.left);
        merged.right = mergeTrees(root1.right, root2.right);
        return merged;
    }
}
