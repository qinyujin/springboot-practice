package algorithm;

/**
 * @author :覃玉锦
 * @create :2021-01-23 10:58:00
 * 判断t2是否是t1的子结构
 */
public class HasSubTreeTest {
    public static void main(String[] args) {
        TreeNode root =new TreeNode(8);//大树根节点
        TreeNode r1=new TreeNode(8);
        TreeNode r2=new TreeNode(2);
        root.right=r2;
        root.left=r1;
        TreeNode r3=new TreeNode(9);
        TreeNode r4=new TreeNode(7);
        r1.right=r4;
        r1.left=r3;

        TreeNode target=new TreeNode(8);//小树根节点
        target.left=new TreeNode(9);
        target.right=new TreeNode(7);

        System.out.println(new HasSubTreeTest().HasSubtree(root,target));
    }

    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if(root1==null||root2==null) return false;
        boolean result=false;
        if(root1.val==root2.val){
            //如果根节点值相等，就去匹配
            result = HasSubtreeHelper(root1,root2);
        }
        //如果当前根结点的值和子树根相等，但是当前结点没有匹配成功，往左子树和右子树再找
        if(!result) result = HasSubtree(root1.left,root2);
        if(!result) result = HasSubtree(root1.right,root2);
        return result;
    }

    public boolean HasSubtreeHelper(TreeNode r1,TreeNode r2){
        //如果子树已经遍历完了，说明匹配成功
        if(r2==null) return true;
        //如果主树完了，子树没有，说明匹配失败
        if(r1==null) return false;
        if(r1.val!=r2.val) return false;
        return HasSubtreeHelper(r1.left,r2.left)&&HasSubtreeHelper(r1.right,r2.right);
    }
}
