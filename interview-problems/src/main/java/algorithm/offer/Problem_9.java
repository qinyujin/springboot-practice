package algorithm.offer;

import java.util.Stack;

/**
 * @author :覃玉锦
 * @create :2021-01-21 16:55:00
 * 用两个栈实现队列
 * https://www.nowcoder.com/practice/54275ddae22f475981afa2244dd448c6?tpId=13&tqId=11158&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 栈的性质决定存入会导致倒序，那么一个栈存入另一个栈同样能实现倒序
 * 两个栈就可以负负得正
 */
public class Problem_9 {
    Stack<Integer> stack1 = new Stack<Integer>();

    Stack<Integer> stack2 = new Stack<Integer>();

    public static void main(String[] args) {
        //队列：先进先出
        Problem_9 queue = new Problem_9();
        queue.push(1);
        queue.push(2);
        queue.push(3);

        System.out.println(queue.pop());
        queue.push(4);
        System.out.println(queue.pop());
        queue.push(6);
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
    }

    public void push(int node) {
        //s1为存储栈，s2为辅助栈
        stack1.push(node);
    }

    public int pop() {
        //s2为空才放数据过来，对数据倒置
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                Integer pop = stack1.pop();
                stack2.push(pop);
            }
        }
        return stack2.pop();
    }
}
