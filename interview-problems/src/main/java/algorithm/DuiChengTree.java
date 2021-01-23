package algorithm;

/**
 * @author :覃玉锦
 * @create :2021-01-23 20:59:00
 * https://www.nowcoder.com/practice/ff05d44dfdb04e1d83bdbdab320efbcb?tpId=13&tqId=11211&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 请实现一个函数，用来判断一棵二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
 */
public class DuiChengTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(8);
        TreeNode r1 = new TreeNode(6);
        TreeNode r2 = new TreeNode(6);
        root.left = r1;
        root.right = r2;

        r1.left = new TreeNode(5);
        r1.right = new TreeNode(7);
        r2.left = new TreeNode(7);
        r2.right = new TreeNode(5);

        boolean symmetrical = new DuiChengTree().isSymmetrical(root);
        System.out.println(symmetrical);
    }

    boolean isSymmetrical(TreeNode pRoot)
    {
        return pRoot==null || judge(pRoot.left,pRoot.right);
    }

    private boolean judge(TreeNode node1,TreeNode node2){
        if(node1==null && node2==null){
            return true;
        }
        else if(node1==null || node2==null){
            return false;
        }

        if(node1.val != node2.val){
            return false;
        }
        else {
            return judge(node1.left,node2.right) && judge(node1.right,node2.left);
        }
    }
}
