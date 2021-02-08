package algorithm;

/**
 * @author :覃玉锦
 * @create :2021-01-22 17:28:00
 * 正则表达式匹配
 * https://www.nowcoder.com/practice/45327ae22b7b413ea21df13ee7d6429c?tpId=13&tqId=11205&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 实现正则表达式匹配的功能。
 * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
 */
public class Problem_19 {
    public static void main(String[] args) {
        char[] str = "aaa".toCharArray();
        char[] pattern = "a*a".toCharArray();
        Problem_19 rem = new Problem_19();
        System.out.println(rem.match(str, pattern));
    }

    public boolean match(char[] str, char[] pattern) {
        //手动实现太难了，还是用方法把
        String s = new String(str);
        String s1 = new String(pattern);
        return s.matches(s1);
    }
}
