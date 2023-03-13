package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2021-03-18 19:03:00
 * 分割等和子集
 * https://leetcode-cn.com/problems/partition-equal-subset-sum/
 */
public class Problem_416 {
    public static void main(String[] args) {
        Problem_416 p = new Problem_416();
//        int[] nums = {1,5,11,5}; //true
        int[] nums = {1,2,3,5}; //false
        System.out.println(p.canPartition(nums));
    }

    //可以转换成背包问题，理解为dp[i][j]中，j为背包容量，在1-i区间中放入元素，最后等于j.
    //定义dp[i][j] 为从1-i区间中是否有集合可以使和等于j
    //每一个数字可以选择或不选择，对应的公式为：dp[i][j] = dp[i-1][j](当前不选择，则结果取决于0至i-1的部分) || dp[i-1][j-nums[i]](当前选择了，则j变为j-nums[i])
    public boolean canPartition(int[] nums) {
        int len = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        //奇数是找不到的，因为只要能分为两组一样的，那么奇数*2 或者偶数*2都是偶数
        if ((sum & 1) == 1) return false;

        //整体拆为部分，即讨论 x = sum/2 以及剩余数字能否构成x的问题。
        int target = sum / 2;
        //len即物品，target+1 是容量，从0到target所以要+1
        boolean[][] dp = new boolean[len][target + 1];
        //dp[0][x] : 小等于0的区间，是否可以装入x
        if (nums[0] <= target) {
            dp[0][nums[0]] = true;
        }

        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= target; j++) {
                //先继承上一行的情况，再特殊判断
                dp[i][j] = dp[i - 1][j];

                //如果nums[i] == j 说明在[0,i]中只取i就可以等于j了
                if (nums[i] == j) {
                    dp[i][j] = true;
                    continue;
                }

                //如果nums[i] < j 可以把nums[i]先放入背包即 j-nums[i]
                if (nums[i] < j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }
            }
        }
        return dp[len-1][target];
    }
}
