package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2021-03-11 12:04:01
 * 爬楼梯
 * https://leetcode-cn.com/problems/climbing-stairs/
 *
 * 一次爬1或2.
 */
public class Problem_70 {
    public static void main(String[] args) {
        Problem_70 p = new Problem_70();
        System.out.println(p.climbStairs(3));
    }

    //n阶楼梯可以拆分为 走1步 + 走f[n-1]步 和走2步+f[n-2]步
    public int climbStairs(int n) {
        if(n<3)return n;
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n];
    }
}
