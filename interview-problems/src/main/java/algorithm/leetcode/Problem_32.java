package algorithm.leetcode;

import java.util.Stack;

/**
 * @author :覃玉锦
 * @create :2021-03-09 12:18:00
 * 最长有效括号
 * https://leetcode-cn.com/problems/longest-valid-parentheses/
 */
public class Problem_32 {
    public static void main(String[] args) {
        Problem_32 p = new Problem_32();
        System.out.println(p.longestValidParentheses("(()"));
    }

    //思路：使用栈来完成。例如演示数据：())(())   答案应该是4，即(())
    public int longestValidParentheses(String s) {
        //匹配到左括号下标入栈，右括号则弹出，然后和栈顶计算计算，也就是和前两个来计算。
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int length = 0, maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') stack.push(i);
            else {
                //右括号需要匹配左括号
                Integer pop = stack.pop();
                if (stack.isEmpty()) stack.push(i);
                length = i - stack.peek();
                maxLength = Math.max(maxLength, length);
            }
        }
        return maxLength;
    }
}
