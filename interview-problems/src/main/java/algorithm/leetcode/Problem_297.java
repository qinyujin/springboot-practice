package algorithm.leetcode;

import algorithm.offer.TreeNode;

/**
 * @author :覃玉锦
 * @create :2021-03-15 17:23:01
 * 二叉树的序列化和反序列化
 * https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/
 * <p>
 * 剑指37
 */
public class Problem_297 {
    public static void main(String[] args) {
        Problem_297 p = new Problem_297();
        TreeNode root = new TreeNode(1);
        TreeNode r1 = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = r1;
        r1.left = new TreeNode(4);
        r1.right = new TreeNode(5);
        String serialize = p.serialize(root);
        System.out.println("序列化字符串：" + serialize);
        TreeNode deserialize = p.deserialize(serialize);
        System.out.println("反序列化头结点：" + deserialize.val);
    }

    //编码规则：构造一个前序二叉树，即root.val+serialize(root.left)+... 如果
    public String serialize(TreeNode root) {
        if (root == null) return "#";
        return root.val + " " + serialize(root.left) + " " + serialize(root.right);
    }

    private String str = "";

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        str = data;
        return deserialize();
    }

    public TreeNode deserialize() {
        if (str.length() == 0 || str == "") return null;
        //第一个空格下标
        int index = str.indexOf(" ");
        //是否能解析到节点，并且把节点从原来的串中摘出来
        String node = index == -1 ? str : str.substring(0, index);
        str = index == -1 ? "" : str.substring(index + 1);
        if (node.equals("#")) return null;
        TreeNode root = new TreeNode(Integer.valueOf(node));
        root.left = deserialize();
        root.right = deserialize();
        return root;
    }
}
