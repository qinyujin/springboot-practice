package algorithm.leetcode;

import algorithm.offer.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
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
        r1.left = r2;
        r1.right = r3;
        r2.left = new TreeNode(3);
        r2.right = new TreeNode(4);
        r3.right = new TreeNode(6);
        p.flatten(r1);
        System.out.println();
    }

    //region 空间复杂度o(n)
    LinkedList<TreeNode> queue = new LinkedList<>();

    //通过先序遍历，来把节点都指向right
    public void flatten(TreeNode root) {
        /*preOrder(root);
        TreeNode head = new TreeNode(-1);
        TreeNode cur = head;
        while (cur != null) {
            cur.right = queue.poll();
            cur.left = null;
            cur = cur.right;
        }
        root = head.right;*/
        flattenO1(root, new ArrayDeque<>());
    }

    public void preOrder(TreeNode node) {
        if (node == null) return;
        queue.add(node);
        preOrder(node.left);
        preOrder(node.right);
    }
    //endregion

    //采用原地算法，空间为o(1).stack里只会保存一个数。
    public void flattenO1(TreeNode root, Deque<TreeNode> stack) {
        //设计思路：示例数据的前序遍历会得到结果：1 2 3 4 5 6，如果访问到2结点时，把前一个节点的right指向2，会造成指针丢失，这
        //里例子就是找不到5.那么可以反过来遍历，即6 5 4 3 2 1。这样的话遍历到5的时候可以把5的right指向6，同理其他节点都可以指
        // 向。并且由于是从树底部遍历上来的，不存在指针丢失。前序是根左右，反过来则是右左根。
        if (root == null) return;
        flattenO1(root.right, stack);
        flattenO1(root.left, stack);
        //指向上一个节点
        if (!stack.isEmpty()) {
            root.right = stack.pop();
        }
        root.left = null;
        stack.push(root);
    }
}
