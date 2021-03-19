package algorithm.leetcode;

import algorithm.offer.ListNode;

/**
 * @author :覃玉锦
 * @create :2021-03-12 15:15:00
 * 环形链表2
 * https://leetcode-cn.com/problems/linked-list-cycle-ii/
 */
public class Problem_142 {
    public static void main(String[] args) {
        Problem_142 p = new Problem_142();
        ListNode n3 = new ListNode(3);
        ListNode n2 = new ListNode(2);
        ListNode n0 = new ListNode(0);
        ListNode n4 = new ListNode(4);
        n3.next = n2;n2.next = n0;n0.next = n4;n4.next = n2;
        ListNode listNode = p.detectCycle(n3);
        System.out.println(listNode.val);
    }

    //快慢指针o(1)空间o(n)时间。思路：先通过快慢指针判断是否存在环，如果存在下一步找入口节点
    //需要统计出环中节点数量，然后让p1先走n，p2再从头来走，相遇就是头
    public ListNode detectCycle(ListNode head) {
        //0或者1个节点没有环
        if(head==null || head.next==null)return null;
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast!=slow){
            if(fast==null || fast.next==null)return null;
            fast = fast.next.next;
            slow = slow.next;
        }
        //相遇了之后查找出环节点数量
        int circleCount = 1;
        fast = fast.next;
        while (fast!=slow){
            fast = fast.next;
            circleCount++;
        }
        //从头开始走，从next开始就相当于先走一步。p1先走count，p2再走
        ListNode p1 = head;
        ListNode p2 = head;
        for (int i = 0; i < circleCount; i++) {
            p1 = p1.next;
        }
        while (p1!=p2){
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }
}
