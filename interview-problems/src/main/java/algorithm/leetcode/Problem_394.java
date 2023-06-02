package algorithm.leetcode;

import java.util.Stack;

/**
 * @author :覃玉锦
 * @create :2021-03-17 10:37:00
 * 字符串解码
 * https://leetcode-cn.com/problems/decode-string/
 */
public class Problem_394 {
    public static void main(String[] args) {
        Problem_394 p = new Problem_394();
        System.out.println(p.decodeString("3[a]2[bc]"));
        /*System.out.println(p.decodeString("3[a2[c]]"));
        System.out.println(p.decodeString("2[abc]3[cd]ef"));
        System.out.println(p.decodeString("abc3[cd]xyz"));
        System.out.println(p.decodeString("leetcode"));*/
    }

    String src;

    int ptr;

    public String decodeString(String s) {
        src = s;
        ptr = 0;
        return getString();
    }

    public String getString() {
        if (ptr == src.length() || src.charAt(ptr) == ']') {
            // String -> EPS
            return "";
        }

        char cur = src.charAt(ptr);
        int repTime = 1;
        String ret = "";

        if (Character.isDigit(cur)) {
            // String -> Digits [ String ] String
            // 解析 Digits
            repTime = getDigits();
            // 过滤左括号
            ++ptr;
            // 解析 String
            String str = getString();
            // 过滤右括号
            ++ptr;
            // 构造字符串
            while (repTime-- > 0) {
                ret += str;
            }
        } else if (Character.isLetter(cur)) {
            // String -> Char String
            // 解析 Char
            ret = String.valueOf(src.charAt(ptr++));
        }

        return ret + getString();
    }

    public int getDigits() {
        int ret = 0;
        while (ptr < src.length() && Character.isDigit(src.charAt(ptr))) {
            ret = ret * 10 + src.charAt(ptr++) - '0';
        }
        return ret;
    }

    //通过栈来完成
    public String decodeString_2(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        StringBuilder cur = new StringBuilder();
        int num = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            } else if (c == '[') {
                numStack.push(num);
                strStack.push(cur);
                cur = new StringBuilder();
                num = 0;
            } else if (c == ']') {
                StringBuilder tmp = cur;
                cur = strStack.pop();
                for (int i = numStack.pop(); i > 0; i--) {
                    cur.append(tmp);
                }
            } else {
                cur.append(c);
            }
        }
        return cur.toString();
    }

}
