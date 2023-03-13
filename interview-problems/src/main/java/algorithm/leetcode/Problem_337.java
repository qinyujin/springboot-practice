package algorithm.leetcode;

import algorithm.offer.TreeNode;

import java.util.HashMap;

/**
 * @author :覃玉锦
 * @create :2021-03-16 12:01:00
 * 打家劫舍
 * https://leetcode-cn.com/problems/house-robber-iii/
 */
public class Problem_337 {

    public static void main(String[] args) {
        Problem_337 p = new Problem_337();
        TreeNode root = new TreeNode(3);
        TreeNode r2 = new TreeNode(2);
        TreeNode r3 = new TreeNode(3);
        root.left = r2;
        root.right = r3;
        r2.right = new TreeNode(3);
        r3.right = new TreeNode(1);
        int rob = p.rob_1(root);
        System.out.println(rob);
    }

    //由于相邻不能选取，抽象3层树，爷爷、儿子、孙子。1个爷爷最多2个儿子，4个孙子。可选组合为爷孙、儿两种.时间限制未通过
    //爷孙：money=root.val + rob(root.left.left) + rob(root.left.right) + rob(root.right.right) + rob(root.right.left)
    //儿子：money=rob(root.left) + rob(root.right)
    public int rob_1(TreeNode root) {
        if (root == null) return 0;

        int money = root.val;
        if (root.left != null) {
            money += rob_1(root.left.left) + rob_1(root.left.right);
        }

        if (root.right != null) {
            money += rob_1(root.right.left) + rob_1(root.right.right);
        }

        money = Math.max(money, (rob_1(root.left) + rob_1(root.right)));
        return money;
    }

    //1的基础上加上缓存，由于每一次方法都会遍历儿子、孙子等节点，直接放入缓存后，不再重复遍历.通过了
    public int rob_2(TreeNode root, HashMap<TreeNode, Integer> map) {
        if (root == null) return 0;
        if (map.containsKey(root)) return map.get(root);

        int money = root.val;
        if (root.left != null) {
            money += rob_2(root.left.left, map) + rob_2(root.left.right, map);
        }

        if (root.right != null) {
            money += rob_2(root.right.left, map) + rob_2(root.right.right, map);
        }

        money = Math.max(money, (rob_2(root.left, map) + rob_2(root.right, map)));
        map.put(root, money);
        return money;
    }

    //解法3，不再在每一层都计算其他层的元素情况，仅考虑当前节点。设int[2] arr. arr[0]代表节点不选 arr[1]代表节点选择了。
    //当前节点选择，则值为左孩子不偷时获得的钱+右孩子不偷时的钱+当前的钱。当前不选：则为左能偷的钱(左子可选也可能不选)+右子钱
    //时间最快
    public int rob_3(TreeNode root) {
        int[] res = rob_3_method(root);
        return Math.max(res[0], res[1]);
    }

    public int[] rob_3_method(TreeNode root) {
        if (root == null) return new int[2];
        int[] result = new int[2];

        int[] left = rob_3_method(root.left);
        int[] right = rob_3_method(root.right);

        //当前不选
        result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        //当前选择
        result[1] = left[0] + right[0] + root.val;
        return result;
    }

}
