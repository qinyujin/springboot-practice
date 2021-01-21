package algorithm;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author :覃玉锦
 * @create :2021-01-21 13:30:00
 * 从尾到头打印链表
 * 题目描述
 * 从尾到头反过来打印出每个结点的值。
 */
public class ReverseShowLinkedList {

    public static void main(String[] args) {
        int[] arr = {67, 0, 24, 58};
        ListNode root = new ListNode(67);
        ListNode temp = root;
        for (int i = 1; i < arr.length; i++) {
            ListNode node = new ListNode(arr[i]);
            temp.next = node;
            temp = temp.next;
        }
        ReverseShowLinkedList rs = new ReverseShowLinkedList();
        System.out.println(rs.printListFromTailToHead(root));
    }

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        //可以使用栈、头插法、递归方式
        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> retList = new ArrayList<>();
        if (listNode == null) {
            return retList;
        }
        //栈
        /*while (listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            retList.add(pop);
        }*/

        //头插法
        while (listNode!=null){
            retList.add(0, listNode.val);
            listNode = listNode.next;
        }
        return retList;
    }
}

class ListNode {
    int val;

    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}