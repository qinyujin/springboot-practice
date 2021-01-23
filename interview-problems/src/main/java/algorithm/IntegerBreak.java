package algorithm;

/**
 * @author :覃玉锦
 * @create :2021-01-22 13:52:00
 * 整数拆分
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 */
public class IntegerBreak {
    public static void main(String[] args) {
        IntegerBreak ib = new IntegerBreak();
        System.out.println(ib.integerBreak(10));
    }

    public int integerBreak(int n) {
        //使用动态规划解法：n至少可以拆分为两段：dp[i] = j和i-j。或者多段：dp[i] = j和dp[i-j]
        int[] dp = new int[n+1];
        dp[1] = dp[2] = 1;
        //从n=3开始
        for (int i = 3; i <= n; i++) {
            //开始分段，长度从1到n
            for (int j = 1; j < i; j++) {
                //1、dp[i] = j * (i-j) 2、dp[i] = j * dp[i-j]
                dp[i] = Math.max(dp[i],Math.max(j*(i-j), j*dp[i-j]));
            }
        }
        return dp[n];
    }
}
