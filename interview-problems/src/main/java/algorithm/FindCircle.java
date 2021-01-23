package algorithm;

/**
 * @author :覃玉锦
 * @create :2021-01-22 19:44:00
 * 判断链表中是否有环。
 * https://www.nowcoder.com/practice/253d2c59ec3e4bc68da16833f79a38e4?tpId=13&tqId=11208&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
 *
 * 思路：
 * 用快指针（一次走两）和慢指针一起走，如果快指针能遇到慢指针就是有环。
 * 如何求入口：
 * 环的个数（通过相遇点再走一圈来获得）为n，设置两个指针p1和p2，p1先走n步，然后p1和p2一起走，相遇即为入口
 */
public class FindCircle {
    public static void main(String[] args) {
        ListNode r1 = new ListNode(1);
        ListNode r2 = new ListNode(2);
        ListNode r3 = new ListNode(3);
        ListNode r4 = new ListNode(4);
        ListNode r5 = new ListNode(5);
        ListNode r6 = new ListNode(6);

        r1.next = r2;
        r2.next = r3;
        r3.next = r4;
        r4.next = r5;
        r5.next = r6;
        r6.next = r3;

        FindCircle fc = new FindCircle();
        ListNode node = fc.EntryNodeOfLoop(r1);
        System.out.println(node);

    }

    public ListNode EntryNodeOfLoop(ListNode pHead)
    {
        if(pHead==null || pHead.next==null){
            return null;
        }
        //1、先判断是否有环
        ListNode fast=pHead,slow=pHead;
        while (fast!=null){
            fast = fast.next;
            fast = fast.next;
            slow = slow.next;
            if(fast==null){
                return null;
            }
            else if(fast == slow){
                break;
            }
        }
        //相遇说明有环，求出环的数量
        int count = 0;
        while (true){
            count++;
            fast = fast.next;
            if(fast == slow){
                break;
            }
        }
        //找到环的数量后，p1走count，然后p1和p2一起走，相遇就是入口
        ListNode p1 = pHead;
        ListNode p2 = pHead;
        for (int i = 0; i < count; i++) {
            p1 = p1.next;
        }
        while (p1!=p2){
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }
}
