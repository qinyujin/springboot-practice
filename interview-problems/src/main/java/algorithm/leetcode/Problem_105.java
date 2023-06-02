package algorithm.leetcode;

import algorithm.offer.TreeNode;

import java.util.HashMap;

/**
 * @author :覃玉锦
 * @create :2021-03-12 12:09:01
 * 前序+中序构建二叉树
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
public class Problem_105 {
    public static void main(String[] args) {
        Problem_105 p = new Problem_105();
        int[] pre = {3,9,20,15,7};
        int[] in = {9,3,15,20,7};
        TreeNode treeNode = p.buildTree(pre, in);
        System.out.println();
    }

    private HashMap<Integer,Integer> indexesOfIn = new HashMap<Integer,Integer>();
    //思路：通过前序确定根节点，通过中序和根节点确定左右size。然后递归进行判断
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            indexesOfIn.put(inorder[i], i);
        }
        return buildTree(preorder,0, preorder.length-1,0,inorder.length-1);
    }

    //3,9,20,15,7  9,3,15,20,7
    public TreeNode buildTree(int[] pre,int preL,int preR,int inL,int inR){
        if(preL > preR || inL > inR)return null;
        //前序为根、左、右，因此前面的都是根节点
        TreeNode root = new TreeNode(pre[preL]);
        //根节点把中序拆分成左右两部分
        int mid = indexesOfIn.get(root.val);
        //左子树的元素数和右子树的元素数，通过这两个可以确定前序里左右子树范围的划分。中序的范围划分只需要通过mid即可判断出来
        int leftSize = mid - inL;
        int rightSize = inR-mid;
        root.left = buildTree(pre,preL+1,preL+leftSize,inL,mid-1);
        root.right = buildTree(pre, preR - rightSize + 1, preR, mid + 1, inR);
        return root;
    }
}
