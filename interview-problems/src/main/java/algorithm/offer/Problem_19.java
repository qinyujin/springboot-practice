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

    //i为str下标，j为pattern下标，那么当str[i]==pattern[j]或者pattern[j]为.则匹配成功。如果是*，那么有三种情况，*前字符为0，为1，为多
    public boolean match(char[] str, char[] pattern) {
        int m = str.length,n = pattern.length;
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        for (int i = 1; i <= n; i++)
            if(pattern[i-1]=='*')
                dp[0][i] = dp[0][i-2];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if(str[i-1] == pattern[j-1] || pattern[j-1]=='.')
                    dp[i][j] = dp[i-1][j-1];
                else if(pattern[j-1]=='*'){
                    if(str[i-1] == pattern[j-2] || pattern[j-2] =='.'){
                        dp[i][j] |= dp[i][j-1]; //a* 字符为1个
                        dp[i][j] |= dp[i-1][j]; //a* 字符为多个
                        dp[i][j] |= dp[i][j-2]; //a* 字符为0个
                    }
                    else dp[i][j] = dp[i][j-2];
                }
            }
        }
        return dp[m][n];
    }
}
