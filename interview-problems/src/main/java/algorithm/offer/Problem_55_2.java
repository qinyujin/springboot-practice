package algorithm.offer;

/**
 * @author :覃玉锦
 * @create :2021-01-28 15:43:00
 * 判断是否平衡二叉树
 * https://www.nowcoder.com/practice/8b3b95850edb4115918ecebdf1b4d222?tpId=13&tqId=11192&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
 * 在这里，我们只需要考虑其平衡性，不需要考虑其是不是排序二叉树
 * 平衡二叉树（Balanced Binary Tree），具有以下性质：它是一棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树。
 */
public class Problem_55_2 {
    public static void main(String[] args) {
        Problem_55_2 problem552 = new Problem_55_2();
        TreeNode root = new TreeNode(10);
        TreeNode r1 = new TreeNode(5);
        root.left = r1;
        root.right = new TreeNode(12);
        r1.left = new TreeNode(4);
        r1.right = new TreeNode(7);
        System.out.println(problem552.IsBalanced_Solution(root));
    }

    private boolean isBalanced = true;
    public boolean IsBalanced_Solution(TreeNode root) {
        height(root);
        return isBalanced;
    }

    public int height(TreeNode root){
        if(root==null || !isBalanced){
            return 0;
        }
        int left = height(root.left);
        int right = height(root.right);
        if(Math.abs(left-right) > 1){
            isBalanced = false;
        }
        return 1+Math.max(left,right);
    }
}
