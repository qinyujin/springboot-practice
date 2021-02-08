package algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author :覃玉锦
 * @create :2021-01-25 19:50:00
 * 从上往下打印二叉树
 * https://www.nowcoder.com/practice/7fe2212963db4790b57431d9ed259701?tpId=13&tqId=11175&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
 * <p>
 * 利用bfs来遍历二叉树即可
 */
public class Problem_32_1 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode r1 = new TreeNode(2);
        TreeNode r2 = new TreeNode(3);
        r1.left = new TreeNode(4);
        r1.right = new TreeNode(5);
        r2.left = new TreeNode(6);
        r2.right = new TreeNode(7);
        root.left = r1;
        root.right = r2;
//        new PrintTreeFromUpToDown().PrintFromTopToBottom(root);
//        new PrintTreeFromUpToDown().Print(root);
        new Problem_32_1().Print2(root);
    }

    /**
     * 从上到下遍历二叉树，即bfs
     *
     * @param root
     * @return
     */
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        //bfs遍历
        Queue<TreeNode> queue = new LinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();

        queue.add(root);
        //遍历队列的元素
        while (!queue.isEmpty()) {
            int count = queue.size();
            while (count-- > 0) {
                TreeNode t = queue.poll();
                //先不加t的判空,不加会有空指针
                if (t == null) {
                    continue;
                }
                list.add(t.val);
                queue.add(t.left);
                queue.add(t.right);
            }
        }
        return list;
    }

    /**
     * 从上倒下遍历二叉树的过程中，保存每一层的数据到list中
     *
     * @param pRoot
     * @return
     */
    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        //bfs遍历
        Queue<TreeNode> queue = new LinkedList<>();
        ArrayList<ArrayList<Integer>> allList = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();

        queue.add(pRoot);
        while (!queue.isEmpty()) {
            int count = queue.size();
            ArrayList<Integer> tempList = new ArrayList<>();
            tempList.addAll(list);
            if (!tempList.isEmpty()) {
                allList.add(tempList);
            }
            list.clear();
            while (count-- > 0) {
                TreeNode t = queue.poll();
                //先不加t的判空,不加会有空指针
                if (t == null) {
                    continue;
                }
                list.add(t.val);
                queue.add(t.left);
                queue.add(t.right);
            }
        }
        return allList;
    }

    /**
     * 从上到下遍历的过程中，按照“之”来遍历，如第一层向右，第二层向左
     *
     * @param pRoot
     * @return
     */
    public ArrayList<ArrayList<Integer>> Print2(TreeNode pRoot) {
        //bfs遍历
        Queue<TreeNode> queue = new LinkedList<>();
        ArrayList<ArrayList<Integer>> allList = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();

        queue.add(pRoot);
        int flag = 1;
        while (!queue.isEmpty()) {
            int count = queue.size();
            ArrayList<Integer> tempList = new ArrayList<>();
            //按照正序/倒序来添加到tempList，然后从list中移除
            if (flag == 1 && !list.isEmpty()) {
                tempList.addAll(list);
                list.clear();
                flag = -1;
            } else {
                while (!list.isEmpty()) {
                    Integer remove = list.remove(list.size() - 1);
                    tempList.add(remove);
                    flag = 1;
                }
            }
            if (!tempList.isEmpty()) {
                allList.add(tempList);
            }
            while (count-- > 0) {
                TreeNode t = queue.poll();
                //先不加t的判空,不加会有空指针
                if (t == null) {
                    continue;
                }
                list.add(t.val);
                queue.add(t.left);
                queue.add(t.right);
            }
        }
        return allList;
    }
}
