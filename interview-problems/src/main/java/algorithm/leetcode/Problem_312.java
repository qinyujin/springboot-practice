package algorithm.leetcode;

import java.util.ArrayDeque;

/**
 * @author :覃玉锦
 * @create :2021-03-19 22:23:00
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
    //状态转移方程为 dp[l][r] = dp[l][k] + temp[l]*temp[k]*temp[r] + dp[k][r] 由于单独元素的左右看成1，所以
    //开辟一个temp数组，头尾设置为1方便处理
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] temp = new int[n+2];
        //用于处理没有的时候就乘1的情况
        temp[0] = temp[n+1] = 1;
        for (int i = 1; i <= n; i++) {
            temp[i] = nums[i-1];
        }
        int[][] dp = new int[n+2][n+2];
        //间距从为[3,n+2]
        for (int len = 3; len <= n + 2; len++) {
            //左端点l，从0到n+2-len即最后留下一个len的长度
            for (int l = 0; l <= n + 2 - len; l++) {
                int res = 0;
                //右端点r为l+间距
                int r = l+len-1;
                //k在(l,r)从左到右走
                for (int k = l+1; k < r; k++) {
                    int left = dp[l][k];
                    int right = dp[k][r];
                    res = Math.max(res,left + temp[l]*temp[k]*temp[r] + right);
                }
                dp[l][r] = res;
            }
        }
        //(n,n+1)区间的最大值
        return dp[0][n+1];
    }
}
