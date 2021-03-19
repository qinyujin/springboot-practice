package algorithm.leetcode;

import algorithm.offer.ListNode;

/**
 * @author :覃玉锦
 * @create :2021-03-04 11:50:00
 * 两个升序链表合并
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/
 */
public class Problem_21 {
    public static void main(String[] args) {

    }

    //思路：新建一个head，然后遍历两条链表，取较小值连接在phead这个链表后面。最后剩余的一条链表也连上即可
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode preHead = new ListNode(Integer.MIN_VALUE);
        ListNode pre = preHead;
        //只需处理完一条链表,剩余的直接连上即可
        while (l1 != null && l2 != null) {
            pre.next = l1.val < l2.val ? l1 : l2;
            if (pre.next == l1) l1 = l1.next;
            if (pre.next == l2) l2 = l2.next;
            pre = pre.next;
        }
        //剩余链表连上即可
        pre.next = l1!=null ? l1 : l2;
        return preHead.next;
    }
}
