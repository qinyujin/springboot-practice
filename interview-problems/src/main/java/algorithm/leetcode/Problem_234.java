package algorithm.leetcode;

import algorithm.offer.ListNode;

/**
 * @author :覃玉锦
 * @create :2021-03-12 20:23:00
 * 回文链表
 * https://leetcode-cn.com/problems/palindrome-linked-list/
 */
public class Problem_234 {
    public static void main(String[] args) {
        Problem_234 p = new Problem_234();
        ListNode r1 = new ListNode(1);
        ListNode r2 = new ListNode(2);
        ListNode r3 = new ListNode(1);
        r1.next = r2;
//        r2.next = r3;
        System.out.println(p.isPalindrome(r1));
    }

    //利用双指针找到中间，这里的中间如果是奇数，中点应该算做前面部分。然后把后半段反转，从头开始对比。最后保留结果，记得把
    //链表反转回去
    //1 2 1 -> 1 2(mid)   1(reverse)     1 2 2 1 -> 1 2(mid)    1 2(reverse)
    public boolean isPalindrome(ListNode head) {
        if(head==null)return true;
        //找出中点(奇数则为前部分)
        ListNode midNode = findMidNode(head);
        //反转后面的链表，mid.next
        ListNode lastList = reverseList(midNode.next);
        //进行回文比较,例如 1221 -> 12 12
        ListNode p1 = head;
        ListNode p2 = lastList;
        boolean res = true;
        while (res && p2!=null){
            if(p1.val!= p2.val)res = false;
            p1 = p1.next;
            p2 = p2.next;
        }
        //还原链表，两次反转即还原了
        reverseList(lastList);
        return res;
    }

    public ListNode findMidNode(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next!=null && fast.next.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public ListNode reverseList(ListNode head){
        ListNode prev = null;
        ListNode cur = head;
        while (cur!=null){
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        //cur = null prev = 反转后的头
        return prev;
    }
}
