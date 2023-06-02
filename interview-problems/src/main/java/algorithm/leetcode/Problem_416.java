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
        int[] nums = {1, 2, 3, 5}; //false
        System.out.println(p.canPartition(nums));
    }

    //可以转换成背包问题，理解为dp[i][j]中，j为背包容量，在1-i区间中放入元素，最后等于j.
    //定义dp[i][j] 为从1-i区间中是否有集合可以使和等于j
    //每一个数字可以选择或不选择，对应的公式为：dp[i][j] = dp[i-1][j](当前不选择，则结果取决于0至i-1的部分) || dp[i-1][j-nums[i]](当前选择了，则j变为j-nums[i])
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        //奇数不可能分成两个相等的
        if (sum % 2 != 0) return false;

        //问题转换为能否在数组中找到和为sum/2的集合，找到了则可分为两部分
        int target = sum / 2;

        int len = nums.length;

        //dp[i][j] : [1,i]区间是否有满足和为j的情况
        boolean[][] dp = new boolean[len+1][target + 1];
        for (int i = 0; i <= len; i++) {
            //都不选择
            dp[i][0] = true;
        }

        for (int i = 1; i <= target; i++) {
            //都不选择则和肯定不是
            dp[0][i] = false;
        }

        //dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]]   i不选择或选择
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= target; j++) {
                if(j < nums[i-1]){
                    dp[i][j] = dp[i-1][j];
                }else {
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]];
                }
            }
        }

        return dp[len][target];
    }
}
