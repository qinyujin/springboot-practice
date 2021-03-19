package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2021-03-11 10:45:00
 * 最大子序列和
 * https://leetcode-cn.com/problems/maximum-subarray/
 */
public class Problem_53 {
    public static void main(String[] args) {
        Problem_53 p =new Problem_53();
//        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};  //6
//        int[] nums = {1}; //1
//        int[] nums = {0}; //0
//        int[] nums = {-1}; //-1
        int[] nums = {-100000}; //-100000
        System.out.println(p.maxSubArray(nums));
    }

    //思路：使用max保存最大值，sum来计算。如果sum<=0那么说明sum不会对接下来的加法变大。那么sum应该置为当前值。
    public int maxSubArray(int[] nums) {
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            sum = sum <=0 ? nums[i] : sum+nums[i];
            max = Math.max(max,sum);
        }
        return max;
    }
}
