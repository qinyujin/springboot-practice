import java.util.Map;
import java.util.Set;

/**
 * @author :覃玉锦
 * @create :2021-01-19 22:44:00
 */
public class Test {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode h1_1 = new TreeNode(2);
        TreeNode h1_2 = new TreeNode(2);
        TreeNode h2_1 = new TreeNode(3);
        TreeNode h2_2 = new TreeNode(4);
        TreeNode h2_3 = new TreeNode(4);
        TreeNode h2_4 = new TreeNode(3);
        root.left = h1_1;
        root.right = h1_2;

        h1_1.left = h2_1;
        h1_1.right = h2_2;
        h1_2.left = h2_3;
        h1_2.right = h2_4;

        /*root.left=h1_1;
        root.right = h1_2;
        h1_1.right = h2_1;
        h1_2.right = h2_4;*/

        System.out.println(isTreeSymmetric(root));
    }
    // 以下给出TreeNode类, 请勿修改
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static boolean isTreeSymmetric(TreeNode root) {
        return isSame(root,root);
    }

    public static boolean isSame(TreeNode t1, TreeNode t2){
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        // 根节点值的比较，左节点和右节点比较，右节点和左节点比较
//        return t1.val == t2.val && isSame(t1.left,t2.right) && isSame(t1.right,t2.left);
        return t1.val == t2.val && isSame(t1.left,t2.right);
    }

    public static Set<String> showMap(Map<String, Object> map) {
        //TODO your code goes here...
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : map.keySet()) {
            //map的处理：使用key.前缀

        }
        return null;
    }

    public static boolean searchMatrix( int target,int[][] array) {
        // Todo your code goes here...
        //可以使用二分查找
        //可以先横向查找，查找到一个范围，只剩两个数。然后对这两个数对应的所有行再次查找。
        int left = 0, right = array[0].length - 1, rowLen = array[0].length, colLen = array.length;
        if (target > array[colLen - 1][rowLen - 1] || target < 0) {
            return false;
        }
        //o(nlogn)
        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[0][mid] == target) {
                return true;
            } else if (array[0][mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        //确定了范围
        for (int i = 0; i < colLen; i++) {
            if (left < rowLen) {
                if (array[i][left] == target) {
                    return true;
                }
            }
        }

        for (int i = 0; i < colLen; i++) {
            if (right >= 0) {
                if (array[i][right] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int binarySearch(int[][] array, int x) {
        int left = 0, right = array[0].length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[0][mid] == x) {
                return mid;
            } else if (array[0][mid] > x) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(left);
        System.out.println(right);
        return -1;
    }

    public static int test(int i) {
        try {
            if (i++ > 0) {
                System.out.println("错误");
            }
            return i++;
        } finally {
            System.out.println("final返回值" + i + i);
            return ++i;
        }
    }

    public String replaceSpace(StringBuilder str) {
        String s = str.toString();
        String replace = s.replace(" ", "%20");
        return replace;
    }

}
