package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2021-03-19 10:23:01
 * 目标和
 * https://leetcode-cn.com/problems/target-sum/
 */
public class Problem_494 {
    public static void main(String[] args) {
        Problem_494 p = new Problem_494();
//        int[] nums = {1,1,1,1,1};
        int[] nums = {1};
        System.out.println(p.findTargetSumWays(nums, 3));
    }

    //1, 1, 1, 1, 1  target = 3    答案是5 即通过加减组合有5种情况可以使和为3
    //类似背包问题，可定义dp[i][j] 为在0-i范围内求值等于j的数量。状态转移方程为dp[i][j] = dp[i-1][j-nums[i]] + dp[i-1][j+nums[i]]
    //https://leetcode-cn.com/problems/target-sum/solution/dong-tai-gui-hua-si-kao-quan-guo-cheng-by-keepal/
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int num : nums) {
            sum+=num;
        }
        if(Math.abs(S) > Math.abs(sum))return 0;
        //从-sum到sum，所以开2*sum+1 0在中间占1.由于下标从0开始表示，但实际范围是-sum至sum，因此按照sum代表0的基准.即后续表
        // 示0用sum来表示.dp[0][sum]表示表格中的0位置
        int[][] dp = new int[nums.length][(sum*2)+1];
        //dp[0][0] 若nums[0]为0，则可以为 -0 +0  | +0 -0 两种情况。
        if(nums[0]==0){
            dp[0][sum] = 2;
        }
        else {
            dp[0][sum-nums[0]] = 1;
            dp[0][sum+nums[0]] = 1;
        }

        //从nums[1]来
        for (int i = 1; i < nums.length; i++) {
            //从-sum开始
            for (int j = 0; j < sum * 2 + 1; j++) {
                //nums[i]可以减掉也可以加上
                //保证不越界
                int l = j-nums[i] >=0 ? j-nums[i] : 0;
                int r = j+nums[i] < sum*2+1 ? j+nums[i] : 0;
                dp[i][j] = dp[i-1][l] + dp[i-1][r];
            }
        }

        //由于负数的存在，sum下标对应着0的位置，所以sum+S就是目标数的dp位置
        return dp[nums.length-1][sum+S];
    }
}
