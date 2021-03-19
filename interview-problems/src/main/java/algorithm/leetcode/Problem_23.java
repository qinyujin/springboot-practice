package algorithm.leetcode;

import algorithm.offer.ListNode;

import java.util.PriorityQueue;

/**
 * @author :覃玉锦
 * @create :2021-03-09 11:18:00
 * k个升序链表合并
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/
 */
public class Problem_23 {
    public static void main(String[] args) {
        Problem_23 p = new Problem_23();
        ListNode[] lists = new ListNode[3];
        ListNode r1 = new ListNode(1);
        ListNode r2 = new ListNode(4);
        ListNode r3 = new ListNode(5);
        r1.next = r2;r2.next = r3;
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(3);
        ListNode n3 = new ListNode(4);
        n1.next = n2;n2.next = n3;
        ListNode m1 = new ListNode(2);
        ListNode m2 = new ListNode(6);
        m1.next = m2;
        lists[0]=r1;
        lists[1]=n1;
        lists[2]=m1;
        ListNode listNode = p.mergeKLists(lists);
    }

    public ListNode mergeKLists(ListNode[] lists) {
        //思路：使用小顶堆结构
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (ListNode list : lists) {
            while (list!=null){
                queue.offer(list.val);
                list = list.next;
            }
        }
        ListNode head = new ListNode(-1);
        ListNode tail = head;
        while (!queue.isEmpty()){
            tail.next = new ListNode(queue.poll());
            tail = tail.next;
        }
        return head.next;
    }
}
