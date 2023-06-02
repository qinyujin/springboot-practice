package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2021-03-15 17:58:00
 * 最佳买卖股票时期含冷冻期
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 */
public class Problem_309 {
    public static void main(String[] args) {
        Problem_309 p = new Problem_309();
        int[] prices = {1,2,3,0,2};
        System.out.println(p.maxProfit(prices));
    }

    //根据题目，一天可以有三种状态：
    //1、持股
    //2、不持股且当前没卖出
    //3、不持股，当前卖出
    public int maxProfit(int[] prices) {
        //dp[i][0] 表示未持股，当天不卖出状态
        //dp[i][1] 表示持股
        //dp[i][2] 表示未持股，当天卖出
        int[][] dp = new int[prices.length][3];
        //第一天未持股不卖出应该是0
        dp[0][0] = 0;
        //第一天持股即表示买入，支出为prices[0]
        dp[0][1] = -prices[0];
        //第一天持股卖出相当于买入又卖出为0
        dp[0][2] = 0;

        for (int i = 1; i < prices.length; i++) {
            //i天不持股不卖出，如果昨天持股的话，那么今天不持股只能是卖出了，因此昨天必须是不持股
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][2]);
            //i天持股昨天一定不可能是卖出(冷冻期),其他两种都可能
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0]-prices[i]);
            //不持股卖出，那么昨天只能是持股然后今天才能卖
            dp[i][2] = dp[i-1][1]+prices[i];
        }
        //最后一天统统选择不持股，全部卖出
        return Math.max(dp[prices.length-1][0],dp[prices.length-1][2]);
    }
}
