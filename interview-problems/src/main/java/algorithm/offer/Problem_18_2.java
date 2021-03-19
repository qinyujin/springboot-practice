package algorithm.offer;

/**
 * @author :覃玉锦
 * @create :2021-01-22 15:53:00
 * https://www.nowcoder.com/practice/fc533c45b73a41b0b44ccba763f866ef?tpId=13&tqId=11209&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 */
public class Problem_18_2 {
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

        Problem_18_2 dr = new Problem_18_2();
        dr.deleteDuplication(root);
        System.out.println();
    }

    //o(1) 时间复杂度的删除节点：如果不是尾节点，那么可以把当前节点的值设置成next的值，然后把next指向下下个。
    //即相当于删除当前的节点。如果是尾节点，那么需要找到它的前一个节点，然后使用cur.next = null
    public ListNode deleteNode(ListNode head, ListNode tobeDelete) {
        //如果是非尾节点
        if (tobeDelete.next != null) {
            ListNode next = tobeDelete.next;
            tobeDelete.val = next.val;
            tobeDelete.next = next.next;
        } else {
            //如果头节点是尾节点，直接删除
            if (head == tobeDelete) head = null;
            //如果是正常的尾节点，那么需要找到前节点。
            else {
                ListNode cur = head;
                while (cur.next != tobeDelete) cur = cur.next;
                cur.next = null;
            }
        }
        return head;
    }

    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return pHead;
        }
        ListNode head = new ListNode(Integer.MIN_VALUE);
        head.next = pHead;
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (cur.next != null && cur.next.val == cur.val) {
                while (cur.next != null && cur.next.val == cur.val) {
                    cur = cur.next;
                }
                cur = cur.next;
                pre.next = cur;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }
        return head.next;
    }
}
