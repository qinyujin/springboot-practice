package algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author :覃玉锦
 * @create :2021-01-21 13:45:00
 * 重建二叉树。
 * 通过前序和中序还原一颗二叉树
 */
public class RebuildBinaryTree {
    public static void main(String[] args) {
        RebuildBinaryTree binaryTree = new RebuildBinaryTree();
        int[] pre = {1, 2, 3, 4, 5, 6, 7};
        int[] in = {3, 2, 4, 1, 6, 5, 7};
        System.out.println(binaryTree.reConstructBinaryTree(pre, in));
    }

    private Map<Integer,Integer> indexOfInOrder = new HashMap<>();

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        for (int i = 0; i < in.length; i++) {
            indexOfInOrder.put(in[i],i);
        }
        return dfs(pre,0,pre.length-1,0,in.length-1);
    }

    /**
     * 对当前的树进行拆分，根据前序/后序和中序
     * @param pre 前序树
     * @param preL 当前树前序左
     * @param preR 当前树前序右
     * @param inL 当前树中序左
     * @param inR 当前树中序右
     * @return
     */
    public TreeNode dfs(int[] pre,int preL,int preR,int inL,int inR){
        if(preL > preR || inL > inR){
            return null;
        }
        //前序数组左下标为当前的根
        TreeNode root = new TreeNode(pre[preL]);
        //根节点中序中对应的下标
        int mid = indexOfInOrder.get(root.val);
        //中序中找出左子树数量
        int leftTreeCount = mid - inL;
        //中序中找出右子树数量
        int rightTreeCount = inR - mid;
        //左子树递归
        root.left = dfs(pre,preL+1,preL+leftTreeCount,inL,mid-1);
        root.right = dfs(pre,preR-rightTreeCount+1,preR,mid+1,inR);
        return root;
    }
}

class TreeNode {
    int val;

    TreeNode left;

    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TreeNode{");
        sb.append("val=").append(val);
        sb.append(", left=").append(left);
        sb.append(", right=").append(right);
        sb.append('}');
        return sb.toString();
    }
}
