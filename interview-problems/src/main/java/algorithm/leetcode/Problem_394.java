package algorithm.leetcode;

import java.util.LinkedList;

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
        LinkedList<Integer> numStack = new LinkedList();
        LinkedList<String> strStack = new LinkedList<>();

        int multi = 0;
        StringBuilder res = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                multi = multi * 10 + Integer.parseInt(c + "");
            } else if (c == '[') {
                numStack.addLast(multi);
                strStack.addLast(res.toString());
                multi = 0;
                res = new StringBuilder();
            } else if (c == ']') {
                StringBuilder temp = new StringBuilder();
                Integer times = numStack.removeLast();
                for (Integer i = 0; i < times; i++) {
                    temp.append(res);
                }
                res = new StringBuilder(strStack.removeLast() + temp);
            } else {
                res.append(c);
            }
        }

        return res.toString();
    }
}
