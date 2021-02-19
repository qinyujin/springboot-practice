package algorithm;

/**
 * @author :覃玉锦
 * @create :2021-01-28 14:42:00
 * 两个链表的第一个公共节点
 * https://www.nowcoder.com/practice/6ab1d9a29e88450685099d45c9e31e46?tpId=13&tqId=11189&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 输入两个链表，找出它们的第一个公共结点。（注意因为传入数据是链表，所以错误测试数据的提示是用其他方式显示的，保证传入数据是正确的）
 */
public class Problem_52 {
    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        ListNode h2 = new ListNode(2);
        ListNode head2 = new ListNode(3);
        ListNode h3 = new ListNode(4);
        ListNode h4 = new ListNode(5);
        ListNode com1 = new ListNode(7);
        ListNode com2 = new ListNode(8);
        ListNode com3 = new ListNode(9);

        head1.next = h2;
        head2.next = h3;
        h3.next = h4;
        com1.next = com2;
        com2.next = com3;
        h2.next = com1;
        h4.next = com1;

        Problem_52 t = new Problem_52();
        ListNode listNode = t.FindFirstCommonNode(head1, head2);
        System.out.println();
    }

    /**
     * 假设c为公共段长度，链表1的总长度为a+c，2为b+c，由a+c+b = b+c+a 因此链表1走完a+c之后，指向链表2，再走，同样
     * 链表2走完b+c之后指向链表1再走，由于符合等式，链表1走完剩余b段，2走完剩余a段正好相遇
     * @param pHead1
     * @param pHead2
     * @return
     */
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode l1 = pHead1,l2 = pHead2;
        while (l1!=l2){
            l1=l1==null?pHead2:l1.next;
            l2=l2==null? pHead1:l2.next;
        }
        return l1;
    }
}
