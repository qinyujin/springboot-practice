package algorithm.leetcode;

import algorithm.offer.ListNode;

/**
 * @author :覃玉锦
 * @create :2021-03-12 14:57:00
 * 环形链表
 * https://leetcode-cn.com/problems/linked-list-cycle/
 */
public class Problem_141 {
    public static void main(String[] args) {
        Problem_141 p = new Problem_141();
        ListNode n1 = new ListNode(3);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(0);
        ListNode n4 = new ListNode(-4);
        n1.next = n2;n2.next = n3;n3.next = n4;n4.next = n2;
        System.out.println(p.hasCycle(n1));
    }

    //思路：使用快慢指针来判断是否含有环
    public boolean hasCycle(ListNode head) {
        if(head ==null || head.next==null)return false;
        ListNode fast = head.next;
        ListNode slow = head;
       while (fast!=slow){
           if(fast==null || fast.next==null)return false;
           fast = fast.next.next;
           slow = slow.next;
       }
       return true;
    }
}
