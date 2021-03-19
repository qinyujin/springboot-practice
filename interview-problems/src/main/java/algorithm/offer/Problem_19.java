package algorithm.offer;

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

    //i-2->x*出现0次，因为下标移动两位。 i-1 ->1次
    //思路：匹配字符相等/pattern为'.' 匹配成功
    //如果是pattern是*，那么需要判断*前面的字符的情况。
    //1、如果*前面是'.'或者是相等，那么有三种情况:x* 出现0、出现1、出现多次
    //2、如果*前面不等，那么只能出现0次。
    public boolean match(char[] str, char[] pattern) {
        int m = str.length, n = pattern.length;
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        //处理s第一个字符，由于pattern前面必须要有字符，所以至少也是a*的形式来出现。那么
        for (int i = 1; i <= n; i++)
            if (pattern[i - 1] == '*')
                dp[0][i] = dp[0][i - 2];

        //这里使用i-1表示当前字符，i即为下一个，如.如*。需要判断下一个是.还是*来特殊处理
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                //如果str i-1 和pattern j-1 相等或者是 j-1为. 说明i可以匹配成功。
                if (str[i - 1] == pattern[j - 1] || pattern[j - 1] == '.')
                    dp[i][j] = dp[i - 1][j - 1];
                    //如果j-1 是* 需要另外讨论
                else if (pattern[j - 1] == '*') {
                    //例如i-1 为 a j-2和j-1 为 a* 那么这里有几种情况
                    //如果i-1 和j-2 字符相等，或者是j-2是. 那么是可以匹配成功。 .*
                    if (str[i - 1] == pattern[j - 2] || pattern[j - 2] == '.') {
                        dp[i][j] |= dp[i][j - 1]; //a* count as 1
                        dp[i][j] |= dp[i - 1][j]; //a* count as n
                        dp[i][j] |= dp[i][j - 2]; //a* count as 0
                        //如果说*前的字符和i不一样，那么a* 数量必须为0
                    } else dp[i][j] = dp[i][j - 2]; //a* count as 0
                }
            }
        }
        return dp[m][n];
    }
}
