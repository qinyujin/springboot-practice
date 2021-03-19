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
        root.left = r1;root.right = r2;
        r2.right = new TreeNode(11);
        r1.left = r3;r1.right = r4;
        r3.left = new TreeNode(3);r3.right = new TreeNode(-2);
        r4.right = new TreeNode(1);
        System.out.println(p.pathSum(root, 8));
    }

    public int pathSum(TreeNode root, int sum) {
        //key：前缀和 value：前缀和数量
        Map<Integer,Integer> prefixSum = new HashMap<>();
        prefixSum.put(0,1);
        return recursionPathSum(root,prefixSum,sum,0);
    }

    public int recursionPathSum(TreeNode node,Map<Integer,Integer> prefixSum,int target,int curSum){
        if(node==null)return 0;
        int res = 0;
        curSum+=node.val;
        //举例，例如路径-3->11 -3的前缀和为7，11的前缀和为18。根节点前缀和为10 18-10(根)=8(target) 则说明根到10
        //之间有一条路径满足target。即数量为1.其他的路径也是一样的来判断。因此如果curSum(18)-target(8)的前缀和数量
        //即10的数量即为路径
        res += prefixSum.getOrDefault(curSum-target, 0);
        //前缀和数量+1或者0+1
        prefixSum.put(curSum,prefixSum.getOrDefault(curSum,0)+1);
        res += recursionPathSum(node.left,prefixSum,target,curSum);
        res += recursionPathSum(node.right,prefixSum,target,curSum);

        prefixSum.put(curSum,prefixSum.get(curSum)-1);
        return res;
    }
}
