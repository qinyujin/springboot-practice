package algorithm.offer;

import java.util.ArrayList;

/**
 * @author :覃玉锦
 * @create :2021-01-26 09:58:00
 * 二叉树中和为某值的路径
 * https://www.nowcoder.com/practice/b736e784e3e34731af99065031301bca?tpId=13&tqId=11177&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 输入一颗二叉树的根节点和一个整数，按字典序打印出二叉树中结点值的和为输入整数的所有路径。路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 */
public class Problem_34 {
    public static void main(String[] args) {
        int target = 22;
        TreeNode root = new TreeNode(10);
        TreeNode r1 = new TreeNode(5);
        root.right = new TreeNode(12);
        root.left = r1;
        r1.left = new TreeNode(4);
        r1.right = new TreeNode(7);

        System.out.println(new Problem_34().FindPath(root, target));
    }

    private ArrayList<ArrayList<Integer>> retList = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        searchPath(root,target, new ArrayList<>());
        return retList;
    }

    public void searchPath(TreeNode node,int target,ArrayList<Integer> path){
        if(node==null){
            return;
        }
        path.add(node.val);
        target -= node.val;
        //找到了路径 这里定义的路径是指从根节点到叶子节点，因此除了判断target，还需要判断左右孩子
        if(target==0 && node.left == null && node.right==null){
            retList.add(new ArrayList<>(path));
        }
        else {
            searchPath(node.left,target,path);
            searchPath(node.right,target,path);
        }
        path.remove(path.size()-1);
    }
}
