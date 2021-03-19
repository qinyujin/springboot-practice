package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2021-03-03 13:26:00
 * 正则表达式，可以参考剑指offer的19题
 */
public class Problem_10 {
    public static void main(String[] args) {
        Problem_10 p = new Problem_10();
        String str = "abcd";
        String pattern = "d*";
        System.out.println(p.isMatch(str, pattern));
    }

    //i-2->x*出现0次，因为下标移动两位。 i-1 ->1次
    public boolean isMatch(String s, String p) {
        //思路：匹配字符相等/pattern为. 匹配成功
        //如果是pattern是*，那么需要判断*前面的字符的情况。
        //1、如果*前面是.或者是相等，那么有三种情况:x* 出现0、出现1、出现多次
        //2、如果*前面不等，那么只能出现0次。
        int m = s.length(),n=p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        //处理s第一个字符，遍历p时如果遇到* 那必须为0.
        for (int i = 1; i <= n; i++)
            if(p.charAt(i-1) == '*')
                dp[0][i] = dp[0][i-2];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) =='.'){
                    dp[i][j] = dp[i-1][j-1];
                }
                else if(p.charAt(j-1) =='*'){
                    if(s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2)=='.'){
                        dp[i][j] |= dp[i][j-1]; //a* count as 1
                        dp[i][j] |= dp[i-1][j]; //a* count as n
                        dp[i][j] |= dp[i][j-2]; //a* count as 0
                    }
                    else {
                        dp[i][j] = dp[i][j-2];
                    }
                }
            }
        }
        return dp[m][n];
    }
}
