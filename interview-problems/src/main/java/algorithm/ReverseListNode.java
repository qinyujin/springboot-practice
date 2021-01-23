package algorithm;

/**
 * @author :覃玉锦
 * @create :2021-01-22 20:00:00
 * 反转链表
 */
public class ReverseListNode {
    public static void main(String[] args) {
        ListNode r1 = new ListNode(1);
        ListNode r2 = new ListNode(2);
        ListNode r3 = new ListNode(3);

        r1.next = r2;
        r2.next = r3;

        ReverseListNode rln = new ReverseListNode();
        ListNode node = rln.ReverseList(r1);
        System.out.println();
    }

    public ListNode ReverseList(ListNode head) {
        if(head==null || head.next==null){
            return head;
        }
        //头插法解决
        ListNode node = new ListNode(0);
        ListNode cur = head;

        while (cur!=null){
            ListNode temp = new ListNode(cur.val);
            temp.next = node.next;
            node.next = temp;
            cur = cur.next;
        }
        return node.next;
    }

}
