package algorithm.leetcode;

import algorithm.offer.ListNode;

/**
 * @author :覃玉锦
 * @create :2021-03-25 09:25:00
 * 两数相加
 * https://leetcode-cn.com/problems/add-two-numbers/
 * [2,4,3] [5,6,4]  大于10进位，向右进
 */
public class Problem_2 {
    public static void main(String[] args) {
        Problem_2 p = new Problem_2();
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(4);
        l1.next = l2;
        l2.next = new ListNode(3);
        ListNode n1 = new ListNode(5);
        ListNode n2 = new ListNode(6);
        n1.next = n2;
        n2.next = new ListNode(4);
        ListNode listNode = p.addTwoNumbers(l1, n1);
        System.out.println(listNode.val);
        System.out.println(listNode.next.val);
        System.out.println(listNode.next.next.val);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carray = 0;
        ListNode head = null, tail = null;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carray;
            if (head == null) {
                tail = new ListNode(sum % 10);
                head = tail;
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carray = sum / 10;
            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
        }
        if (carray > 0) tail.next = new ListNode(carray);
        return head;
    }
}
