package algorithm.leetcode;

import algorithm.offer.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author :覃玉锦
 * @create :2021-03-19 09:31:00
 * 路径总和Ⅲ
 * https://leetcode-cn.com/problems/path-sum-iii/
 */
public class Problem_437 {
    public static void main(String[] args) {
        Problem_437 p = new Problem_437();
        TreeNode root = new TreeNode(10);
        TreeNode r1 = new TreeNode(5);
        TreeNode r2 = new TreeNode(-3);
        TreeNode r3 = new TreeNode(3);
        TreeNode r4 = new TreeNode(2);
        root.left = r1;
        root.right = r2;
        r2.right = new TreeNode(11);
        r1.left = r3;
        r1.right = r4;
        r3.left = new TreeNode(3);
        r3.right = new TreeNode(-2);
        r4.right = new TreeNode(1);
        System.out.println(p.pathSum(root, 8));
    }

    public int pathSum(TreeNode root, int sum) {
        //key：前缀和 value：前缀和数量
        Map<Long, Integer> prefixMap = new HashMap<>();
        prefixMap.put(0L, 1);
        return recursionPathSum(root, prefixMap, sum, 0L);
    }

    public int recursionPathSum(TreeNode node, Map<Long, Integer> map, int target, long curSum) {
        if (node == null) return 0;
        int res = 0;
        curSum += node.val;
        //举例:在此题示例数据中，前缀和为18会出现3次(其他的都是1次),
        res += map.getOrDefault(curSum - target, 0);
        //前缀和数量+1或者0+1
        map.put(curSum, map.getOrDefault(curSum, 0) + 1);
        res += recursionPathSum(node.left, map, target, curSum);
        res += recursionPathSum(node.right, map, target, curSum);

        //分别递归了左子树、右子树。比如把左子树的前缀和遍历完了准备遍历右子树的时候，没删除map，那么右子树会使用现在的map进行重复计算
        //但是一个节点只能选择左子或右子
        map.put(curSum, map.getOrDefault(curSum, 0) - 1);
        return res;
    }
}
