package algorithm;

/**
 * @author :覃玉锦
 * @create :2021-01-22 15:53:00
 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 */
public class DeleteRepeatLinkedNode {
    static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        ListNode root = new ListNode(1);
        ListNode r2 = new ListNode(2);
        ListNode r3 = new ListNode(3);
        ListNode r7 = new ListNode(3);
        ListNode r4 = new ListNode(4);
        ListNode r8 = new ListNode(4);
        ListNode r5 = new ListNode(5);

        root.next = r2;
        r2.next = r3;
        r3.next = r7;
        r7.next = r4;
        r4.next = r8;
        r8.next = r5;

        DeleteRepeatLinkedNode dr = new DeleteRepeatLinkedNode();
        dr.deleteDuplication(root);
        System.out.println();
    }

    public ListNode deleteDuplication(ListNode pHead)
    {
        if(pHead == null || pHead.next == null){
            return pHead;
        }
        ListNode head = new ListNode(Integer.MIN_VALUE);
        head.next = pHead;
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur!=null){
            if(cur.next!=null && cur.next.val == cur.val){
                while (cur.next!=null && cur.next.val == cur.val){
                    cur = cur.next;
                }
                cur = cur.next;
                pre.next = cur;
            }
            else {
                pre = cur;
                cur = cur.next;
            }
        }
        return head.next;
    }
}
