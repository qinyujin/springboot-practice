package algorithm.leetcode;

import algorithm.offer.ListNode;

/**
 * @author :覃玉锦
 * @create :2021-03-12 17:34:00
 * 反转链表
 * https://leetcode-cn.com/problems/reverse-linked-list/
 */
public class Problem_206 {
    public static void main(String[] args) {
        Problem_206 p = new Problem_206();
        ListNode r1 = new ListNode(1);
        ListNode r2 = new ListNode(2);
        ListNode r3 = new ListNode(3);
        r1.next = r2;
        r2.next = r3;
//        ListNode listNode = p.reverseList_iteration(r1);
//        System.out.println(listNode.val);

        ListNode resNode = p.reverseList_recycle(r1);
        System.out.println(resNode);
    }

    //题目建议使用迭代或者递归.迭代法：
    public ListNode reverseList_iteration(ListNode head) {
        //可以使用cur和pre，cur.next = pre即可。这里需要反转，所以cur在pre的后面。那么cur.next = pre就是把后面
        //节点指向前面节点，局部反转。接下来一起后移即可
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    //递归，可以画个栈来理解一下。遍历到最后一个节点，开始往回走，同时做连接操作
    public ListNode reverseList_recycle(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = reverseList_recycle(head.next);
        //从后往前链
        head.next.next = head;
        head.next = null;
        return cur;
    }
}
