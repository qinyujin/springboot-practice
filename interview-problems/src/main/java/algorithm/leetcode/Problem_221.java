package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2021-03-15 09:06:00
 * 最大正方形
 * https://leetcode-cn.com/problems/maximal-square/
 */
public class Problem_221 {
    public static void main(String[] args) {
        Problem_221 p = new Problem_221();
        /*char[][] matrix = {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };*/
        /*char[][] matrix = {
                {'0','1'},
                {'1','0'}
        };*/
        char[][] matrix = {
                {'0'}
        };
        int i = p.maximalSquare(matrix);
        System.out.println(i);
    }

    //思路：如果一个位置是0，则dp[i][j] = 0 。如果是1，则说明该部分有可能组成正方形，则从左、上、左上中寻找dp最小值+1
    //例如这三个部分都是1，即都是单独正方形，那么当前的dp为2说明组成一个大正方形
    public int maximalSquare(char[][] matrix) {
        //如果长度不超过2，那么正方形最大只能是0或者1
        int[][] dp = new int[matrix.length][matrix[0].length];
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            if(matrix[i][0] == '0')dp[i][0] = 0;
            else {
                dp[i][0] = 1;
                max = 1;
            }
        }
        for (int i = 0; i < matrix[0].length; i++) {
            if(matrix[0][i] == '0') dp[0][i] = 0;
            else {
                dp[0][i] = 1;
                max = 1;
            }
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (matrix[i][j] == '1') dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]))+1;
                else dp[i][j] = 0;
                if (dp[i][j] > max) max = dp[i][j];
            }
        }
        return max*max;
    }
}
