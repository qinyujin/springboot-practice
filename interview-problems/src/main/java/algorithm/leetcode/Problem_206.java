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
        r1.next = r2; r2.next = r3;
        ListNode listNode = p.reverseList(r1);
        System.out.println(listNode.val);
    }

    //题目建议使用迭代或者递归
    public ListNode reverseList(ListNode head) {
        //可以使用cur和pre，cur.next = pre即可。这里需要反转，所以cur在pre的后面。那么cur.next = pre就是把后面
        //节点指向前面节点，局部反转。接下来一起后移即可
        ListNode cur = head;
        ListNode pre = null;
        while (cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
