package algorithm;

/**
 * @author :覃玉锦
 * @create :2021-01-28 12:40:00
 * 礼物的最大价值
 * https://www.nowcoder.com/questionTerminal/72a99e28381a407991f2c96d8cb238ab?answerType=1&f=discussion
 * 在一个 m*n 的棋盘的每一个格都放有一个礼物，每个礼物都有一定价值（大于 0）。从左上角开始拿礼物，每次向右或向下移动一格，直到右下角结束。给定一个棋盘，求拿到礼物的最大价值。例如，对于如下棋盘
 * 1    10   3    8
 * 12   2    9    6
 * 5    7    4    11
 * 3    7    16   5
 * 礼物的最大价值为 1+12+5+7+7+16+5=53。
 */
public class Problem_47 {
    public static void main(String[] args) {
        int[][] board = {
                {1,10,3,8},
                {12,2,9,6},
                {5,7,4,11},
                {3,7,16,5}
        };
        System.out.println(new Problem_47().getMost(board));
    }

    public int getMost(int[][] board) {
        // write code here
        //只能往下或者右走，那么对于一个格子来说，只能由上或者左走过来。dp记录每个格子最大的值，选取上或者左中较大值
        int[][] dp = new int[board.length][board[0].length];
        dp[0][0] = board[0][0];
        for (int i = 1; i < dp.length; i++) {
            dp[i][0]+=dp[i-1][0]+board[i][0];
        }
        for (int i = 1; i < dp[0].length; i++) {
            dp[0][i]+=dp[0][i-1]+board[0][i];
        }
        for(int i=1;i<dp.length;i++){
            for (int j=1;j<dp[0].length;j++){
                //找出上/右中最大的加到dp里
                dp[i][j] += board[i][j]+Math.max(dp[i-1][j],dp[i][j-1]);
            }
        }
        int row = dp.length,col = dp[0].length;
        return dp[row-1][col-1];
    }
}
