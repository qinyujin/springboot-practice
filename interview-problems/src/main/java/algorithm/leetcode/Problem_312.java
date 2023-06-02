package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2021-03-19 22:23:01
 * 戳气球
 * https://leetcode-cn.com/problems/burst-balloons/
 */
public class Problem_312 {
    public static void main(String[] args) {
        Problem_312 p = new Problem_312();
//        int[] nums = {3,1,5,8}; //167
        int[] nums = {1,5}; //10
        System.out.println(p.maxCoins(nums));
    }

    //https://leetcode-cn.com/problems/burst-balloons/solution/zhe-ge-cai-pu-zi-ji-zai-jia-ye-neng-zuo-guan-jian-/
    //假设dp[l][r]为区间[l,r]中能获得的最多的金币。
    // 状态转移方程为 dp[l][r] = dp[l][k] + temp[l]*temp[k]*temp[r] + dp[k][r]
    public int maxCoins(int[] nums) {
        int n = nums.length;
        //给nums前后塞个0，方便边界值处理.原nums范围[1,n]
        int[] temp = new int[n+2];
        temp[0] = temp[n+1] = 1;
        for (int i = 1; i <= n; i++) {
            temp[i] = nums[i-1];
        }
        int[][] dp = new int[n+2][n+2];
        //两端点中间间距长度从3起，具体为[3,n+2]
        for (int len = 3; len <= n + 2; len++) {
            //左端点l从0到 n+2-len (里末尾len的地方)
            for (int l = 0; l <= n + 2 - len; l++) {
                int res = 0;
                //右端点
                int r = l+len-1;
                //k在(l,r)从左到右走，同时统计最大气球值. 公式是dp[l][k] + val.l*val.k*val.r + dp[k][r]
                for (int k = l+1; k < r; k++) {
                    res = Math.max(res,dp[l][k] + temp[l]*temp[k]*temp[r] + dp[k][r]);
                }
                dp[l][r] = res;
            }
        }
        //(n,n+1)区间的最大值
        return dp[0][n+1];
    }
}
