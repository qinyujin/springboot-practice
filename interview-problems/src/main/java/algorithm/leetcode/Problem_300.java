package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2021-03-15 17:49:00
 * 最长递增子序列
 * https://leetcode-cn.com/problems/longest-increasing-subsequence/
 */
public class Problem_300 {
    public static void main(String[] args) {
        Problem_300 p = new Problem_300();
//        int[] nums = {10,9,2,5,3,7,101,18};
        int[] nums = {0,1,0,3,2,3};
        System.out.println(p.lengthOfLIS(nums));
    }

    //思路：dp记录当前最长递增长度。
    //   10,9,2,5,3,7,101,18
    //dp: 1,1,1,2,2,3,4,4
    //即对于i元素，从1-i来查找，如果当前元素大于j元素，则可以使用j元素来构成子序列，当前就为dp[j]+1
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int maxn = 1;
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            //最小值是1
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
            maxn = Math.max(maxn, dp[i]);
        }
        return maxn;
    }
}
