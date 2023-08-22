package algorithm.leetcode;

import algorithm.offer.ListNode;

/**
 * @author :覃玉锦
 * @create :2021-03-04 10:52:00
 * 删除链表的倒数第n个节点
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 * 参考剑指offer18
 */
public class Problem_19 {
    public static void main(String[] args) {
        Problem_19 p = new Problem_19();
        ListNode r1 = new ListNode(1);
        ListNode r2 = new ListNode(2);
        ListNode r3 = new ListNode(3);
        r1.next = r2;
        r2.next = r3;
        System.out.println(p.removeNthFromEnd(r1, 2));
    }

    //倒数第n个节点，说明删除节点和尾部间距为n，可以利用这个特点，结合快慢指针来实现
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        ListNode slow = head;

        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        if (fast == null) {
            return head.next;
        }

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return head;
    }
}
