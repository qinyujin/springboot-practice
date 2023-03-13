package algorithm.leetcode;

import algorithm.offer.ListNode;

/**
 * @author :覃玉锦
 * @create :2021-03-12 16:33:00
 * 相交链表
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 */
public class Problem_160 {
    public static void main(String[] args) {
        Problem_160 p = new Problem_160();
        ListNode a1 = new ListNode(4);
        ListNode a2 = new ListNode(1);
        ListNode b1 = new ListNode(5);
        ListNode b2 = new ListNode(0);
        ListNode b3 = new ListNode(1);
        ListNode c1 = new ListNode(8);
        ListNode c2 = new ListNode(4);
        ListNode c3 = new ListNode(5);
        a1.next = a2;a2.next = c1;
        b1.next = b2;b2.next = b3;b3.next = c1;
        c1.next = c2;
        c2.next = c3;
        ListNode intersectionNode = p.getIntersectionNode(a1, b1);
        System.out.println(intersectionNode.val);
    }

    //两个链表的公共节点。思路：a+c+b = b+c+a(c是交点后的公共段)，此公式表明，我们可以让指针1先走完a+c再指向b-head，走完b则会
    //与指针2走完b+c再走a相遇，即两指针相等
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode l1 = headA,l2 = headB;
        while (l1!=l2){
            l1 = l1!=null ? l1.next : headB;
            l2 = l2!=null ? l2.next : headA;
        }
        return l1;
    }
}
