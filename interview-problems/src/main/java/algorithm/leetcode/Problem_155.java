package algorithm.leetcode;

import java.util.Stack;

/**
 * @author :覃玉锦
 * @create :2021-03-12 16:23:01
 * 最小栈
 * https://leetcode-cn.com/problems/min-stack/
 */
public class Problem_155 {
    public static void main(String[] args) {
        Problem_155 p = new Problem_155();
        p.push(-2);
        p.push(0);
        p.push(-3);
        System.out.println(p.getMin());
        p.pop();
    }

    //思路：通过dataStack和minStack来完成。
    private Stack<Integer> dataStack;
    private Stack<Integer> minStack;

    public void push(int x) {
        dataStack.push(x);
        minStack.push(minStack.isEmpty()?x:Math.min(minStack.peek(),x));
    }

    public void pop() {
        dataStack.pop();
        minStack.pop();
    }

    public int top() {
        return dataStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
