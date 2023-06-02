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
        int[] nums = {0, 1, 0, 3, 2, 3};
        System.out.println(p.lengthOfLIS(nums));
    }

    //思路：dp记录当前最长递增长度。时间o(n^2)
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
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxn = Math.max(maxn, dp[i]);
        }
        return maxn;
    }

    //改进思路：第二层的循环，可以考虑把dp设置为一个递增数组，那么就可以使用二分了，时间复杂度则可以降到o(nlogn).其中递增的时候
    //可以考虑用贪心思路，如 1 5 3,可以认为1 5的最终序列长度没有1 3的最终序列长度长，因为3比较小，更有机会增加长度
    public int lengthOfLIS_2(int[] nums) {
        //构造一个递增序列
        int[] tails = new int[nums.length];
        //tails序列的长度
        int res = 0;
        for (int num : nums) {
            int i = 0, j = res;
            //通过二分法在tails递增序列中找到num插入的位置,如果tails中找不到这个位置说明num比tails都大，则可以加入到tails后面
            while (i < j) {
                int m = (i + j) / 2;
                if (tails[m] < num) i = m + 1;
                else j = m;
            }
            tails[i] = num;
            //num相对tails保持递增，则num可以加到tails里
            if (res == j) res++;
        }
        return res;
    }
}
