package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2023-03-14 10:30:00
 * <p>
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/
 */
public class Problem_121 {
    public static void main(String[] args) {
        Problem_121 p = new Problem_121();
        System.out.println(p.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }

    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            max = Math.max(max, prices[i] - min);
        }
        return max;
    }
}
