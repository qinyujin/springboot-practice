package algorithm.leetcode;

import algorithm.offer.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author :覃玉锦
 * @create :2021-03-16 12:01:00
 * 打家劫舍
 * https://leetcode-cn.com/problems/house-robber-iii/
 */
public class Problem_337 {

    public static void main(String[] args) {
        Problem_337 p = new Problem_337();
        TreeNode root = new TreeNode(3);
        TreeNode r2 = new TreeNode(2);
        TreeNode r3 = new TreeNode(3);
        root.left = r2;root.right = r3;
        r2.right = new TreeNode(3);
        r3.right = new TreeNode(1);
        int rob = p.rob(root);
        System.out.println(rob);
    }

    //f代表node被选中，g代表node未被选中
    private Map<TreeNode,Integer> f = new HashMap<>();
    private Map<TreeNode,Integer> g = new HashMap<>();
    public int rob(TreeNode root) {
        dfs(root);
        return Math.max(f.getOrDefault(root,0),g.getOrDefault(root,0));
    }

    public void dfs(TreeNode node){
        if(node==null)return;
        dfs(node.left);
        dfs(node.right);
        //root被选中
        f.put(node,node.val + g.getOrDefault(node.left, 0)+g.getOrDefault(node.right,0));
        //root未被选中
        g.put(node,Math.max(f.getOrDefault(node.left,0),g.getOrDefault(node.left,0)
        ) + Math.max(f.getOrDefault(node.right,0),g.getOrDefault(node.right,0)));
    }
}
