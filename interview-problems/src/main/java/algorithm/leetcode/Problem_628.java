package algorithm.leetcode;

import java.util.Arrays;

/**
 * @author :覃玉锦
 * @create :2023-03-21 14:11:01
 * <p>
 * 三个数的最大乘积
 * https://leetcode.cn/problems/maximum-product-of-three-numbers/
 */
public class Problem_628 {

    public static void main(String[] args) {
        Problem_628 p = new Problem_628();
        System.out.println(p.maximumProduct(new int[]{1, 2, 3}));
    }

    //都为正数：乘积最大值为排序数组最后三个数相乘
    //都为负数：乘积最大值为排序数组最后三个数相乘
    //有正有负：（1）乘积最大值为排序数组最后三个数相乘
    //（2）乘积最大值为排序数组前两个负数与数组最后一个正数相乘
    //**整理一下上面的四种情况：**可以归纳成取max（排序数组最后三个数相乘，排序数组前两个负数与数组最后一个正数相乘）
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        return Math.max(nums[0] * nums[1] * nums[n - 1], nums[n - 1] * nums[n - 2] * nums[n - 3]);
    }
}
