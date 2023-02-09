package algorithm;

import algorithm.offer.ListNode;

/**
 * @author :覃玉锦
 * @create :2021-01-21 20:59:00
 * <p>
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * <p>
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 返回容器可以储存的最大水量。
 * <p>
 * 说明：你不能倾斜容器。
 * <p>
 *  
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Practice {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2).next = new ListNode(3);

        System.out.println(function(head, 2));
    }

    //del 倒数n个节点，并返回head
    public static ListNode function(ListNode head, int n) {

        //1 2 3 null
        //del和cur保持固定n的距离，cur=null时del为倒数n.此例假设n=1，那么需要等cur走到3时，del也同步走.
        ListNode cur = head, del = head;
        int count = 0;
        while (cur != null) {
            if (count == n + 2) {
                del = del.next;
            }
            cur = cur.next;
            if (count != n + 1) count++;
        }

        if (count == n) head.next = head.next.next;
        else del.next = del.next.next;

        return head;
    }
}
