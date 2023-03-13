package algorithm.leetcode;

import java.util.Arrays;

/**
 * @author :覃玉锦
 * @create :2021-03-16 10:55:00
 * 零钱兑换
 * https://leetcode-cn.com/problems/coin-change/
 */
public class Problem_322 {
    public static void main(String[] args) {
        Problem_322 p = new Problem_322();
        int[] coins = {1, 2, 5};
        System.out.println(p.coinChange(coins, 11));
    }

    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        //dp[i] 表示i金额需要的最少硬币数
        int[] dp = new int[amount + 1];
        //由于最多的情况是amount枚一元硬币，所以amount+1肯定比所有情况都大。这里是初始化值
        Arrays.fill(dp, max);
        dp[0] = 0;
        //统计从金额1-金额amount最小需要硬币数
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                //i是余额，coins[j]表示硬币面值.第i个值所需最小硬币数可以通过dp[i-[硬币面值]]来得到。
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        //如果没有改动，说明不行，返回-1
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
