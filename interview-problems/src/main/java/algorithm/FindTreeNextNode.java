package algorithm;

/**
 * @author :覃玉锦
 * @create :2021-01-21 16:42:00
 * 二叉树的下一个结点
 * 题目描述
 * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 */
public class FindTreeNextNode {
    public static void main(String[] args) {
        TreeLinkNode root = new TreeLinkNode(1);
        TreeLinkNode n2 = new TreeLinkNode(2);
        TreeLinkNode n3 = new TreeLinkNode(3);
        TreeLinkNode n4 = new TreeLinkNode(4);
        TreeLinkNode n5 = new TreeLinkNode(5);
        TreeLinkNode n6 = new TreeLinkNode(6);
        TreeLinkNode n7 = new TreeLinkNode(7);

        root.left = n2;
        root.right = n3;
        n2.left = n4;
        n2.right = n5;
        n5.left = n6;
        n6.right = n7;
        n2.next = root;
        n3.next = root;
        n4.next = n2;
        n5.next = n2;
        n6.next = n5;
        n7.next = n5;

        FindTreeNextNode ft = new FindTreeNextNode();
        System.out.println(ft.GetNext(n2));
        System.out.println(ft.GetNext(n7));
    }

    public TreeLinkNode GetNext(TreeLinkNode pNode)
    {
        TreeLinkNode temp;
        //右子树不为空，找到右子树的最左子树
        if(pNode.right!=null){
            temp = pNode.right;
            while (temp.left!=null){
                temp = temp.left;
            }
            return temp;
        }
        //没有右子树，需要一直找父级，什么时候停止寻找：当前结点是父级的左子结点
        else {
            while (pNode.next!=null){
                //记录当前结点
                temp = pNode;
                //上移
                pNode = pNode.next;
                //如果当前结点是左子结点
                if(pNode.left == temp){
                    return pNode;
                }
            }
        }
        return null;
    }
}

class TreeLinkNode {

    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null;

    TreeLinkNode(int val) {
        this.val = val;
    }
}
