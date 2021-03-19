package algorithm.leetcode;

import algorithm.offer.TreeNode;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author :覃玉锦
 * @create :2021-03-15 10:14:00
 * 二叉树的最近公共祖先
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 */
public class Problem_236 {
    public static void main(String[] args) {
        Problem_236 p = new Problem_236();
        TreeNode r3 = new TreeNode(3);
        TreeNode r5 = new TreeNode(5);
        TreeNode r1 = new TreeNode(1);
        TreeNode r6 = new TreeNode(6);
        TreeNode r2 = new TreeNode(2);
        TreeNode r0 = new TreeNode(0);
        TreeNode r8 = new TreeNode(8);
        TreeNode r7 = new TreeNode(7);
        TreeNode r4 = new TreeNode(4);
        r3.left = r5;r3.right = r1;
        r5.left = r6;r5.right = r2;
        r1.left = r0;r1.right = r8;
        r2.left =r7;r2.right = r4;
        TreeNode treeNode = p.lowestCommonAncestor(r3, r5, r1);
        System.out.println(treeNode.val);
    }

    //思路：可以使用哈希来存储父节点，先把所有节点的父节点存储起来，形式:key:int value:TreeNode 表示数字的父节点
    //存储父节点之后，可以先让p往上遍历，并且记录节点已经遍历过，然后再q往上遍历，如果遇到一个已经遍历过的节点就是父节点
    private HashMap<Integer,TreeNode> parent = new HashMap<>();
    HashSet<Integer> visited = new HashSet<>();
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root);
        //先把p往上全部走一遍
        while (p!=null){
            visited.add(p.val);
            p = parent.get(p.val);
        }
        //把q全部走一遍，如果遇到已经访问过了的，说明是父节点
        while (q!=null){
            if(visited.contains(q.val))return q;
            q = parent.get(q.val);
        }
        return null;
    }

    public void dfs(TreeNode root){
        //把每个节点对应父节点记录
        if(root.left!=null){
            parent.put(root.left.val,root);
            dfs(root.left);
        }
        if(root.right!=null){
            parent.put(root.right.val,root);
            dfs(root.right);
        }
    }
}
