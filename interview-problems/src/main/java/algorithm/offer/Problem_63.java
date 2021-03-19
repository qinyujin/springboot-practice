package algorithm.offer;

/**
 * @author :覃玉锦
 * @create :2021-01-28 22:06:00
 */
public class Problem_63 {
    public static void main(String[] args) {
        int[] arr = {7,6,4,3,1};
        System.out.println(new Problem_63().maxProfit(arr));
    }

    public int maxProfit(int[] prices) {
        if(prices.length==0)return 0;
        //贪心，找到价格最低的买入
        int min = 9999999;
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            min = Math.min(min,prices[i]);
            max = Math.max(max,prices[i]-min);
        }
        return max;
    }
}
