package algorithm;

/**
 * @author :覃玉锦
 * @create :2021-01-23 20:27:00
 * 二叉树的镜像
 * https://www.nowcoder.com/practice/564f4c26aa584921bc75623e48ca3011?tpId=13&tqId=11171&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 操作给定的二叉树，将其变换为源二叉树的镜像。
 */
public class Problem_27 {
    static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(8);
        TreeNode r1 = new TreeNode(6);
        TreeNode r2 = new TreeNode(10);
        root.left = r1;
        root.right = r2;
        r1.left = new TreeNode(5);
        r1.right = new TreeNode(7);
        r2.left = new TreeNode(9);
        r2.right = new TreeNode(11);

        new Problem_27().Mirror(root);
        System.out.println();
    }

    public void Mirror(TreeNode root) {
        if(root==null)return;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        Mirror(root.left);
        Mirror(root.right);
    }
}
