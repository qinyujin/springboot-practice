package algorithm.leetcode;

import algorithm.offer.TreeNode;

/**
 * @author :覃玉锦
 * @create :2021-03-12 11:22:00
 * 验证二叉搜索树
 * https://leetcode-cn.com/problems/validate-binary-search-tree/
 */
public class Problem_98 {
    public static void main(String[] args) {
        Problem_98 p = new Problem_98();
        TreeNode r1 = new TreeNode(2);
        TreeNode r2 = new TreeNode(1);
        TreeNode r3 = new TreeNode(3);
        r1.left = r2;
        r1.right = r3;
        boolean validBST = p.isValidBST(r1);
        System.out.println(validBST);
    }

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root,Long.MIN_VALUE,Long.MAX_VALUE);
    }

    //通过上下界来检查，如果node在区间内说明ok，在区间外说明false，通过递归调用改变上下界为node的值来判断子节点是否符合
    public boolean isValidBST(TreeNode node,long lower,long upper){
        if(node==null)return true;
        if(node.val <= lower || node.val >= upper)return false;
        return isValidBST(node.left,lower,node.val) && isValidBST(node.right,node.val,upper);
    }
}
