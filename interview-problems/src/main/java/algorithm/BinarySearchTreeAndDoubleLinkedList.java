package algorithm;

/**
 * @author :覃玉锦
 * @create :2021-01-26 10:42:00
 * 二叉搜索树与双向链表
 * https://www.nowcoder.com/practice/947f6eb80d944a84850b0538bf0ec3a5?tpId=13&tqId=11179&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
 *
 * 线索化二叉树
 */
public class BinarySearchTreeAndDoubleLinkedList {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        TreeNode convert = new BinarySearchTreeAndDoubleLinkedList().Convert(root);
        System.out.println();
    }

    private TreeNode pre = null;
    private TreeNode head = null;

    public TreeNode Convert(TreeNode root) {
        inOrder(root);
        return head;
    }

    private void inOrder(TreeNode node) {
        if (node == null)
            return;
        //中序遍历二叉搜索树是增序，因此正好对每一个节点建立指针。left代表前驱结点，right代表后续
        inOrder(node.left);
        //链接前驱
        node.left = pre;
        //通过前驱来链接后续
        if (pre != null)
            pre.right = node;
        pre = node;
        //存储第一个节点为head
        if (head == null)
            head = node;
        inOrder(node.right);
    }
}
