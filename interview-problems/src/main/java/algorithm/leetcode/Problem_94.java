package algorithm.leetcode;

import algorithm.offer.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-03-12 10:55:00
 * 二叉树中序遍历
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 */
public class Problem_94 {
    public static void main(String[] args) {
        Problem_94 p = new Problem_94();
        TreeNode r1 = new TreeNode(1);
        TreeNode r2 = new TreeNode(2);
        TreeNode r3 = new TreeNode(3);
        r1.right = r2;
        r2.left = r3;
        List<Integer> integers = p.inorderTraversal(r1);
        System.out.println(integers);
    }

    private List<Integer> list = new ArrayList<>();
    //中序：左根右
    public List<Integer> inorderTraversal(TreeNode root) {
        infixOrder(root);
        return list;
    }

    public void infixOrder(TreeNode node){
        if(node==null)return;
        infixOrder(node.left);
        list.add(node.val);
        infixOrder(node.right);
    }
}
