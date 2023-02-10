package algorithm;

/**
 * @author :覃玉锦
 * @create :2021-01-21 20:59:00
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/merge-k-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Practice {
    public static void main(String[] args) {
//        System.out.println(function(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
//        System.out.println(function(new int[]{4, 5, 6, 7, 0, 1, 22}, 3));
//        System.out.println(function(new int[]{1}, 0));
//        System.out.println(function(new int[]{1,3}, 3));
//        System.out.println(function(new int[]{1,3}, 1));
        System.out.println(function(new int[]{3, 1}, 1));
    }


    //旋转数组查找target
    public static int function(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
        }
        return -1;
    }
}
