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
        System.out.println(p.longestValidParentheses("()"));
        System.out.println(p.doubleTraverse("()"));
    }

    //思路：使用栈来完成。例如演示数据：())(())   答案应该是4，即(()).使用栈来记录下标，直接用下标来计算数量,因此栈只需要保存左括号的下标，匹配到右括号时计算长度保存。
    //时间:o(n) 空间:o(n)
    public int longestValidParentheses(String s) {
        //匹配到左括号下标入栈，右括号则弹出，然后和栈顶计算长度.
        Stack<Integer> stack = new Stack<>();
        //临界值处理 例如 ()  [-1]->[-1,0] ->[-1] length = 1-(-1) = 2
        stack.push(-1);
        int length = 0, maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') stack.push(i);
            else {
                //右括号需要匹配左括号
                stack.pop();
                //如果没左括号了，那么此右括号的下标为分割线，从分割线往右再找
                if (stack.isEmpty()) stack.push(i);
                length = i - stack.peek();
                maxLength = Math.max(maxLength, length);
            }
        }
        return maxLength;
    }

    //双向遍历  时间o(n) 空间o(1)
    //思路:设有left、right保存左右括号出现数量，当left==right时计算长度，right>left时一起清0，看作重新开始。但有个缺点：(()这样的情况是无法兼顾的，所以需要
    //从右往左再来一遍。条件则是相反，left>right时计算
    public int doubleTraverse(String s) {
        //-1和s.length()是因为临界值
        int left = 0, right = 0, maxLength = 0, leftIndex = -1, rightIndex = s.length();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') left++;
            if (s.charAt(i) == ')') right++;
            if (left == right) {
                maxLength = Math.max(maxLength, i - leftIndex);
            }
            if (right > left) {
                left = 0;
                right = 0;
                leftIndex = i;
            }
        }

        left = 0;
        right = 0;
        for (int j = s.length() - 1; j >= 0; j--) {
            if (s.charAt(j) == '(') left++;
            if (s.charAt(j) == ')') right++;
            if (left == right) {
                maxLength = Math.max(maxLength, rightIndex - j);
            }
            if (left > right) {
                left = 0;
                right = 0;
                rightIndex = j;
            }
        }

        return maxLength;
    }
}
