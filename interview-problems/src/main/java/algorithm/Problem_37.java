package algorithm;

/**
 * @author :覃玉锦
 * @create :2021-01-26 11:17:00
 * 序列化和反序列化二叉树
 * https://www.nowcoder.com/practice/cf7e25aa97c04cc1a68c8f040e71fb84?tpId=13&tqId=11214&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *请实现两个函数，分别用来序列化和反序列化二叉树
 *
 * 二叉树的序列化是指：把一棵二叉树按照某种遍历方式的结果以某种格式保存为字符串，从而使得内存中建立起来的二叉树可以持久保存。序列化可以基于先序、中序、后序、层序的二叉树遍历方式来进行修改，序列化的结果是一个字符串，序列化时通过 某种符号表示空节点（#），以 ！ 表示一个结点值的结束（value!）。
 *
 * 二叉树的反序列化是指：根据某种遍历顺序得到的序列化字符串结果str，重构二叉树。
 *
 * 例如，我们可以把一个只有根节点为1的二叉树序列化为"1,"，然后通过自己的函数来解析回这个二叉树
 */
public class Problem_37 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode r1 = new TreeNode(2);
        TreeNode r2 = new TreeNode(6);
        root.left = r1;
        root.right = r2;
        r1.left = new TreeNode(1);
        r2.left = new TreeNode(5);
        r2.right = new TreeNode(7);
        Problem_37 t = new Problem_37();
        String str = t.Serialize(root);
        TreeNode deserialize = t.Deserialize(str);
        System.out.println();
    }

    private String serializeTree = "";

    //可以使用中序遍历来序列化二叉树
    String Serialize(TreeNode root) {
        if(root==null){
            return "#";
        }
        return root.val + " " + Serialize(root.left) + " " + Serialize(root.right);
    }

    TreeNode Deserialize(String str) {
        //根据中序字符串来还原成二叉树
        serializeTree = str;
        return Deserialize();
    }

    private TreeNode Deserialize(){
        //根据前序的序列创建的树，那么还原时只需遵守根、左、右的顺序递归即可
        if (serializeTree.length() == 0 ||serializeTree=="")
            return null;
        int index = serializeTree.indexOf(" ");
        //拿到第一个字符，即根
        String node = index == -1 ? serializeTree : serializeTree.substring(0,index);
        //对字符串改变，减去当前字符
        serializeTree = index == -1 ? "" :serializeTree.substring(index+1);
        //空的处理
        if (node.equals("#"))
            return null;
        //根节点处理好后，按照前序就是左、右。
        TreeNode t = new TreeNode(Integer.valueOf(node));
        t.left = Deserialize();
        t.right = Deserialize();
        return t;
    }
}
