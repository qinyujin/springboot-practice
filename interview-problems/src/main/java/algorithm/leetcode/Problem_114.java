package algorithm.leetcode;

import algorithm.offer.TreeNode;

import java.util.LinkedList;

/**
 * @author :覃玉锦
 * @create :2021-03-12 14:04:00
 * 二叉树展开为链表
 * https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
 * 二叉树前序遍历转化为链表
 */
public class Problem_114 {
    public static void main(String[] args) {
        Problem_114 p = new Problem_114();
        TreeNode r1 = new TreeNode(1);
        TreeNode r2 = new TreeNode(2);
        TreeNode r3 = new TreeNode(5);
        r1.left = r2;r1.right = r3;
        r2.left = new TreeNode(3);
        r2.right = new TreeNode(4);
        r3.right = new TreeNode(6);
        p.flatten(r1);
        System.out.println();
    }

    LinkedList<TreeNode> queue = new LinkedList<>();
    //通过先序遍历，来把节点都指向right
    public void flatten(TreeNode root) {
        preOrder(root);
        TreeNode head =new TreeNode(-1);
        TreeNode cur = head;
        while (cur!=null){
            cur.right = queue.poll();
            cur.left = null;
            cur = cur.right;
        }
        root = head.right;
    }

    public void preOrder(TreeNode node){
        if(node==null)return;
        queue.add(node);
        preOrder(node.left);
        preOrder(node.right);
    }
}
