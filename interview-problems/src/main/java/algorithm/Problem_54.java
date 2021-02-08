package algorithm;

/**
 * @author :覃玉锦
 * @create :2021-01-28 15:08:00
 * 二叉搜索树的第k小的节点
 * https://www.nowcoder.com/practice/ef068f602dde4d28aab2b210e859150a?tpId=13&tqId=11215&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 给定一棵二叉搜索树，请找出其中的第k小的结点。
 * <p>
 * 思路：二叉搜索树的中序遍历是升序，可以利用
 */
public class Problem_54 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode r1 = new TreeNode(3);
        TreeNode r2 = new TreeNode(7);
        root.left = r1;
        root.right = r2;
        r1.left = new TreeNode(2);
        r1.right = new TreeNode(4);
        r2.left = new TreeNode(6);
        r2.right = new TreeNode(8);

        Problem_54 bin = new Problem_54();
        bin.KthNode(root,3);
    }

    private int count = 0;
    private int k = 0;
    private TreeNode node;
    TreeNode KthNode(TreeNode pRoot, int k) {
        this.k = k;
        inOrder(pRoot);
        return this.node;
    }

    public void inOrder(TreeNode node) {
        if (node == null) return;
        inOrder(node.left);
        count++;
        if(count == this.k){
            this.node = node;
            return;
        }
        inOrder(node.right);
    }
}
