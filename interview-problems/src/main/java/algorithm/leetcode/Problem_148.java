package algorithm.leetcode;

import algorithm.offer.ListNode;

/**
 * @author :覃玉锦
 * @create :2021-03-13 10:57:00
 * 排序链表
 * https://leetcode-cn.com/problems/sort-list/
 */
public class Problem_148 {
    public static void main(String[] args) {
        Problem_148 p = new Problem_148();
        ListNode r1 = new ListNode(4);
        ListNode r2 = new ListNode(2);
        ListNode r3 = new ListNode(1);
        ListNode r4 = new ListNode(3);
        r1.next = r2;
        r2.next = r3;
        r3.next = r4;
        ListNode listNode = p.sortList(r1);
        System.out.println();
    }

    //这题可以借用反转链表局部反转的思想，时间：o(n).题目要求o(nlogn)，所以这里使用归并排序算法
    //思路和归并一样，可以分为三步
    //1、拆。需要找到中点，然后拆分为两个链表。中点的查找可以使用快慢指针。两倍速度。那么slow就是中点
    //2、排序两个子链表
    //3、合并
    public ListNode sortList(ListNode head) {
        return sortList(head,null);
    }

    //head、mid、tail可以对链表进行分段。可以通过快慢指针查找mid
    public ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) return head;
        //拆分。由于左边是[head,mid]，右边是[mid,tail]所以如果是左边的话舍弃掉mid元素.
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        ListNode fast = head;
        ListNode slow = head;
        //保证fast在tail之前
        while (fast != tail) {
            fast = fast.next;
            slow = slow.next;
            if (fast != tail) fast = fast.next;
        }
        ListNode mid = slow;
        ListNode list1 = sortList(head, mid);
        ListNode list2 = sortList(mid, tail);
        ListNode merge = merge(list1, list2);
        return merge;
    }

    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode phead = new ListNode(0);
        ListNode temp = phead, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val < temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if(temp1!=null)temp.next = temp1;
        if(temp2!=null)temp.next = temp2;
        //排序完毕
        return phead.next;
    }
}
