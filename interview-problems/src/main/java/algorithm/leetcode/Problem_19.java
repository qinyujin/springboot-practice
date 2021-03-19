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
        ListNode listNode = p.removeNthFromEnd(r1, 2);
        System.out.println();
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        //思路:当cur 走了 n次，del再走从头一起走。当cur走到空时即del走到了倒数n个节点。目标是为了保持这个n的
        //间距。1 2 3 null  由于有空和需要找前面一个节点。所以间距应该是n+2.即 如果n=1，那么当cur走到3时del再走
        if (head == null) return null;
        ListNode cur = head;
        //删除的前一个节点.
        ListNode del = head;
        int count = 0;
        while (cur != null) {
            count++;
            while (count > n + 1 && cur != null) {
                //一起走到尾
                del = del.next;
                cur = cur.next;
            }
            if (cur != null) cur = cur.next;
        }
        //第一个节点，删除第一个让下一个做头结点
        if (count == n) head = head.next;
        else del.next = del.next.next;
        return head;
    }
}
