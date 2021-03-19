package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2021-03-11 14:20:00
 * 编辑距离
 * https://leetcode-cn.com/problems/edit-distance/
 * <p>
 * 字符串通过增、删、改几次能成为字符串2
 */
public class Problem_72 {
    public static void main(String[] args) {
        Problem_72 p = new Problem_72();
//        System.out.println(p.minDistance("horse", "ros")); //3
        System.out.println(p.minDistance("intention", "execution")); //5
    }

    //思路：假设dp[i][j] 为 字符串1 到i位置变换成 字符串2 到j位置需要多少次。
    //有这么几种情况：增、删、改
    //增：dp[i][j] = dp[i][j-1]+1 为啥它是增：因为本来是i-1，增加一位就是i，所以是dp[i][j-1]
    //删：dp[i][j] = dp[i-1][j]+1
    //改：dp[i][j] = dp[i-1][j-1]+1
    //那如果i-1 和j-1 对应的字符一样，就不用加一
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m+1][n+1];
        //先处理长度为0的情况，两种，一种是word1为0，一种是word2为0.
        //1：
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        //2:
        for (int i = 0; i <= n; i++) {
            dp[0][i] = i;
        }
        //处理全部的情况，即赠删改中最小，然后如果字符一样则不改
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i][j]);
                }
            }
        }
        return dp[m][n];
    }
}
