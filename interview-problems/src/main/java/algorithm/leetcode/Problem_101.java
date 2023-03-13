package algorithm.leetcode;

import algorithm.offer.TreeNode;

/**
 * @author :覃玉锦
 * @create :2021-03-12 11:35:00
 * 对称二叉树
 * https://leetcode-cn.com/problems/symmetric-tree/
 */
public class Problem_101 {
    public static void main(String[] args) {
        Problem_101 p = new Problem_101();
        TreeNode r1 = new TreeNode(1);
        TreeNode r2 = new TreeNode(2);
        TreeNode r3 = new TreeNode(2);
        r1.left = r2;
        r1.right = r3;
        r2.left = new TreeNode(3);
        r2.right = new TreeNode(4);
        r3.left = new TreeNode(4);
        r3.right = new TreeNode(3);
        System.out.println(p.isSymmetric(r1));
    }

    //思路：左子树等于右子树，左的子右等于右的子左
    public boolean isSymmetric(TreeNode root) {
        if(root==null || (root.left ==null && root.right ==null))return true;
        return isSymmetric(root.left,root.right);
    }

    public boolean isSymmetric(TreeNode n1,TreeNode n2){
        //1、两子节点都空对称
        if(n1==null && n2==null)return true;
        //2、有一不空不对称
        if(n1==null || n2==null)return false;
        //3、值不同不对称
        if(n1.val!=n2.val)return false;
        //4、左的左对应右的右，左的右对应右的左
        return isSymmetric(n1.left,n2.right) && isSymmetric(n1.right,n2.left);
    }
}
