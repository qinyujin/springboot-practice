package algorithm.leetcode;

import java.util.Stack;

/**
 * @author :覃玉锦
 * @create :2021-03-04 11:20:01
 * 有效的括号
 * https://leetcode-cn.com/problems/valid-parentheses/
 */
public class Problem_20 {
    public static void main(String[] args) {
        Problem_20 p = new Problem_20();
        System.out.println(p.isValid("()"));
        System.out.println(p.isValid("()[]{}"));
        System.out.println(p.isValid("(]"));
        System.out.println(p.isValid("([)]"));
        System.out.println(p.isValid("{[]}"));
        System.out.println(p.isValid("["));
        System.out.println(p.isValid("]"));
    }

    //栈来实现判断括号是否合法。
    //()[]{} true {[]} true ([)] false
    public boolean isValid(String s) {
        //思路：匹配到右括号时，需要向栈中寻找对应左括号是否存在
        if (s == null || s.length() == 0) return false;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') stack.push(s.charAt(i));
            else {
                //右括号需要匹配对应左括号即为匹配成功，如果栈里没有，自然是不对
                if(stack.isEmpty())return false;
                if (s.charAt(i) == ')') {
                    Character pop = stack.pop();
                    if (pop != '(') return false;
                }
                if (s.charAt(i) == ']') {
                    Character pop = stack.pop();
                    if (pop != '[') return false;
                }
                if (s.charAt(i) == '}') {
                    Character pop = stack.pop();
                    if (pop != '{') return false;
                }
            }
        }
        //如果整个栈都判断结束，说明是对的表达式
        if (stack.size() == 0) return true;
        return false;
    }
}
