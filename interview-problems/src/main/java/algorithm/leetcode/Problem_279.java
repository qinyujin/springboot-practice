package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2021-03-15 17:08:00
 * 完全平方数
 * https://leetcode-cn.com/problems/perfect-squares/
 */
public class Problem_279 {
    public static void main(String[] args) {
        Problem_279 p = new Problem_279();
        System.out.println(p.numSquares(13));
    }

    public int numSquares(int n) {
        int[] dp = new int[n+1];
        for (int i = 1; i <= n; i++) {
            dp[i] = i;
            for(int j = 1;i-j*j >=0;j++){
                //相当于i 可以由 i-j*j 推导出来
                dp[i] = Math.min(dp[i],dp[i-j*j]+1);
            }
        }
        return dp[n];
    }
}
