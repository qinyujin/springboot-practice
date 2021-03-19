package algorithm.offer;

/**
 * @author :覃玉锦
 * @create :2021-01-26 10:31:00
 * 复杂链表的复制
 * https://www.nowcoder.com/practice/f836b2c43afc4b35ad6adc41ec941dba?tpId=13&tqId=11178&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针random指向一个随机节点），请对此链表进行深拷贝，并返回拷贝后的头结点。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
 */
public class Problem_35 {
    public static void main(String[] args) {

    }

    public RandomListNode Clone(RandomListNode pHead)
    {
        if(pHead==null){
            return null;
        }
        //大体思路：1、在每个节点后面建立新节点，用于复制 2、对新节点建立连接关系 3、拆分
        //1、建立新节点
        RandomListNode cur = pHead;
        while (cur!=null){
            RandomListNode clone = new RandomListNode(cur.label);
            clone.next = cur.next;
            cur.next = clone;
            cur = clone.next;
        }
        //2、建立链接
        cur = pHead;
        while (cur!=null){
            RandomListNode clone = cur.next;
            if(cur.random!=null){
                clone.random = cur.random.next;
            }
            cur = clone.next;
        }
        //3、拆分，跳着链接即可
        cur = pHead;
        RandomListNode pCloneHead = pHead.next;
        while (cur.next!=null){
            RandomListNode next = cur.next;
            cur.next = next.next;
            cur = next;
        }
        return pCloneHead;
    }
}
