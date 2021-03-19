package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2021-03-11 11:30:00
 * 不同路径
 * https://leetcode-cn.com/problems/unique-paths/
 *
 * 从0，0 走到m，n 只能向下向右
 */
public class Problem_62 {
    public static void main(String[] args) {
        Problem_62 p = new Problem_62();
        System.out.println(p.uniquePaths(3, 2)); //3
        System.out.println(p.uniquePaths(3, 7)); //7
    }

    public int uniquePaths(int m, int n) {
        //思路：可以使用动态规划，由于只能往下或者右走，那么对于每一项，都可以由i-1，j走过来或者i，j-1走过来。
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
}
