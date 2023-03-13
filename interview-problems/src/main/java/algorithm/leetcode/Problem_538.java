package algorithm.leetcode;

import algorithm.offer.TreeNode;

/**
 * @author :覃玉锦
 * @create :2021-03-19 11:30:00
 * 把二叉搜索树转换为累加树
 * https://leetcode.cn/problems/convert-bst-to-greater-tree/
 */
public class Problem_538 {
    public static void main(String[] args) {
        Problem_538 p = new Problem_538();
        TreeNode r4 = new TreeNode(4);
        TreeNode r1 = new TreeNode(1);
        TreeNode r6 = new TreeNode(6);
        TreeNode r2 = new TreeNode(2);
        TreeNode r7 = new TreeNode(7);
        r4.left =r1;r4.right=r6;
        r1.right = r2;
        r6.right = r7;
        r1.left = new TreeNode(0);
        r2.right = new TreeNode(3);
        r6.left = new TreeNode(5);
        r7.right = new TreeNode(8);
        TreeNode treeNode = p.convertBST(r4);
        System.out.println(treeNode.val);
    }

    private int sum;
    public TreeNode convertBST(TreeNode root) {
        //右左根来递归，然后sum累加即可
        if(root!=null){
            convertBST(root.right);
            sum+=root.val;
            root.val = sum;
            convertBST(root.left);
        }
        return root;
    }
}
