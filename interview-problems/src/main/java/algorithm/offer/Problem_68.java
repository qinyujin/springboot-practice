package algorithm.offer;

/**
 * @author :覃玉锦
 * @create :2021-01-28 22:49:00
 * 书中两个节点最低的公共祖先
 * 1、https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/
 * 二叉查找树中，两个节点 p, q 的公共祖先 root 满足 root.val >= p.val && root.val <= q.val。
 * <p>
 * 2、https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
 * 普通二叉树，在左右子树中查找是否存在 p 或者 q，如果 p 和 q 分别在两个子树中，那么就说明根节点就是最低公共祖先。
 */
public class Problem_68 {
    public static void main(String[] args) {
        TreeNode binaryRoot = new TreeNode(4);
        TreeNode binaryRoot1 = new TreeNode(2);
        TreeNode binaryRoot2 = new TreeNode(6);
        binaryRoot.left = binaryRoot1;
        binaryRoot.right = binaryRoot2;
        binaryRoot1.left = new TreeNode(1);
        binaryRoot1.right = new TreeNode(3);
        binaryRoot2.left = new TreeNode(5);
        binaryRoot2.right = new TreeNode(7);
        Problem_68 problem68 = new Problem_68();
        TreeNode treeNode = problem68.lowestCommonAncestor(binaryRoot, binaryRoot1.left, binaryRoot1.right);

        TreeNode normalRoot = new TreeNode(1);
        TreeNode normalRoot1 = new TreeNode(2);
        TreeNode normalRoot2 = new TreeNode(3);
        normalRoot.left = normalRoot1;
        normalRoot.right = normalRoot2;
        normalRoot1.left = new TreeNode(4);
        normalRoot1.right = new TreeNode(5);
        normalRoot2.left = new TreeNode(6);
        normalRoot2.right = new TreeNode(7);
        TreeNode treeNode1 = problem68.lowestCommonAncestor2(normalRoot, normalRoot1.left, normalRoot1.right);
        System.out.println();
    }

    /**
     * 二叉查找树
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return root;
        //二叉搜索树的特性，小于的往左走，大于的往右走
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        if (root.val < p.val && root.val < q.val){
            return lowestCommonAncestor(root.right,p,q);
        }
        return root;
    }

    /**
     * 普通树
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null || root==p || root == q){
            return root;
        }
        TreeNode left = lowestCommonAncestor2(root.left,p,q);
        TreeNode right = lowestCommonAncestor2(root.right,p,q);
        //左边如果为空返回右边，右边如果为空返回左边，如果都不为空返回root
        return left == null ? right : right ==null? left : root;
    }
}
