package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2021-03-12 16:46:00
 * 打家劫舍
 * https://leetcode-cn.com/problems/house-robber/
 */
public class Problem_198 {
    public static void main(String[] args) {
        Problem_198 p = new Problem_198();
        int[] nums = {2,1,1,2};
        System.out.println(p.rob(nums));
    }

    //1,2,3,1 -> 4 解释：选择1+3。不能选择相邻的，所以1+3为最大。
    //由于条件只是不相邻，所以f[i]可以拆分成小问题：f[i] = max(f[i-2]+nums[i],f[i-1])
    //例如 1,2,3,1 ->dp[1,2,4,4] 因为第四个2+1=3 是小于上一次的，因此取到4
    public int rob(int[] nums) {
        int[] dp = new int[nums.length];
        int max = Integer.MIN_VALUE;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0],nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-2]+nums[i],dp[i-1]);
        }
        return dp[nums.length-1];
    }
}
