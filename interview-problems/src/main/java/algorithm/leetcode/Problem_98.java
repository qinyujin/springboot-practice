package algorithm.leetcode;

import algorithm.offer.TreeNode;

/**
 * @author :覃玉锦
 * @create :2021-03-12 11:22:01
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

    private long pre = Long.MIN_VALUE;
    //中序为左、根、右，正好符合二叉搜索树的排序特点，因此使用中序遍历来判断
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;

        //左子树即使为true，最终结果也还需要判断右子树
        boolean leftValid = isValidBST(root.left);
        //根节点
        boolean valid = root.val > pre;
        pre = root.val;
        //右子树
        boolean rightValid = isValidBST(root.right);
        return leftValid && valid && rightValid;
    }
}
