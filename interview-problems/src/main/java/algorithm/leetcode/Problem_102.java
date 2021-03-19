package algorithm.leetcode;

import algorithm.offer.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-03-12 11:39:00
 * 二叉树层序遍历
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 */
public class Problem_102 {
    public static void main(String[] args) {
        Problem_102 p = new Problem_102();
        TreeNode r1 = new TreeNode(3);
        TreeNode r2 = new TreeNode(20);
        r1.left = new TreeNode(9);
        r1.right = r2;
        r2.left = new TreeNode(15);
        r2.right = new TreeNode(7);
        List<List<Integer>> lists = p.levelOrder(r1);
        System.out.println(lists);
    }

    //层序遍历，即广度优先遍历，利用队列来完成。

    /**
     * 3
     * 9   20
     * 15   7
     * 队列情况:
     * [3]->[9,20]->[15,7]
     * 具体步骤：1、当队列不为空则继续进行 2、拿到当前队列元素数量，即一层的元素 3、对一层进行处理，即while size>0
     * 时，处理对应的逻辑，需要加入当前元素到list，然后把左右子节点加入队列
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        queue.add(root);
        //如果队列不为空说明还有元素
        while (!queue.isEmpty()) {
            int cnt = queue.size();
            //把当前队列中的元素全部处理完，即遍历每一层
            ArrayList<Integer> list = new ArrayList<>();
            while (cnt-- > 0) {
                TreeNode t = queue.poll();
                //由于是左右都加入，有可能出现空，在此处理即可
                if (t == null) continue;
                list.add(t.val);
                queue.add(t.left);
                queue.add(t.right);
            }
            if (list.size() != 0) res.add(list);
        }
        return res;
    }
}
