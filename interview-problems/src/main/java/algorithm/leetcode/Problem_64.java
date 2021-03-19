package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2021-03-11 11:54:00
 * 最小路径和
 * https://leetcode-cn.com/problems/minimum-path-sum/
 */
public class Problem_64 {
    public static void main(String[] args) {
        Problem_64 p = new Problem_64();
        int[][] grid = {
                {1,3,1},
                {1,5,1},
                {4,2,1}
        };
        System.out.println(p.minPathSum(grid));
    }

    //可以使用dp，由于只能往下或者往右，所以 f[i][j] = Max(f[i-1][j],f[i][j-1])
    public int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = dp[i-1][0]+grid[i][0];
        }
        for (int i = 1; i < dp[0].length; i++) {
            dp[0][i] = dp[0][i-1]+grid[0][i];
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i-1][j],dp[i][j-1]);
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
}
