package algorithm;

/**
 * @author :覃玉锦
 * @create :2021-01-28 15:17:00
 */
public class Problem_55_1    {
    public static void main(String[] args) {

    }

    public int TreeDepth(TreeNode root) {
        return root==null?0:1+Math.max(TreeDepth(root.left),TreeDepth(root.right));
    }
}
