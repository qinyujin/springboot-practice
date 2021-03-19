package algorithm.offer;

/**
 * @author :覃玉锦
 * @create :2021-01-22 20:11:00
 * 合并两个排序好的链表
 * https://www.nowcoder.com/practice/d8b6b4358f774294a89de2a6ac4d9337?tpId=13&tqId=11169&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 */
public class Problem_25 {
    public static void main(String[] args) {
        Problem_25 mol = new Problem_25();
        ListNode r1 = new ListNode(1);
        ListNode r2 = new ListNode(3);
        ListNode r3 = new ListNode(5);

        ListNode n1 = new ListNode(2);
        ListNode n2 = new ListNode(4);
        ListNode n3 = new ListNode(6);

        r1.next = r2;
        r2.next = r3;
        n1.next=n2;
        n2.next=n3;
        ListNode merge = mol.Merge(r1, n1);
        System.out.println();
    }

    public ListNode Merge(ListNode list1, ListNode list2) {
        //合并可以采用一个新链表来按序尾插或者把一个链表遍历后插入到另一个链表
        //这里就采用两个链表的做法
        ListNode cur2  = list2;
        while (cur2!=null){
            insert(list1,cur2.val);
            cur2 = cur2.next;
        }
        return list1;
    }

    private void insert(ListNode head, int value) {
        //按序插入
        if (head == null) {
            return;
        }
        //特殊值头插
        if (value < head.val) {
            ListNode node = new ListNode(head.val);
            node.next = head.next;
            head.val = value;
            head.next = node;
            return;
        }
        ListNode cur = head;
        //中间和结尾的值的插入
        while (cur != null) {
            while (cur.next!=null && cur.next.val <= value){
                cur = cur.next;
            }
            //如果是最后一位
            if(cur.next==null){
                cur.next = new ListNode(value);
                break;
            }
            else {
                ListNode node = new ListNode(value);
                node.next = cur.next;
                cur.next = node;
                break;
            }
        }
    }
}
