package algorithm;

/**
 * @author :覃玉锦
 * @create :2021-01-22 18:52:00
 * 输入一个链表，输出该链表中倒数第k个结点。
 */
public class LinkedListLastNode {
    static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("ListNode{");
            sb.append("val=").append(val);
            sb.append('}');
            return sb.toString();
        }
    }

    public ListNode FindKthToTail(ListNode head,int k) {
        if(head==null){
            return null;
        }
        //求出链表长
        int size=0;
        ListNode cur = head;
        while (cur!=null) {
            size++;
            cur = cur.next;
        }
        //通过长度来进行倒序遍历
        cur = head;
        while (cur!=null){
            if(size == k){
                return cur;
            }
            size--;
            cur = cur.next;
        }
        return null;
    }

    public static void main(String[] args) {
        LinkedListLastNode llln = new LinkedListLastNode();
        ListNode root = new ListNode(1);
        ListNode r2 = new ListNode(2);
        ListNode r3 = new ListNode(3);
        ListNode r4 = new ListNode(4);
        ListNode r5 = new ListNode(5);

        root.next = r2;
        r2.next = r3;
        r3.next = r4;
        r4.next = r5;

        System.out.println(llln.FindKthToTail(root, 1));
    }
}
