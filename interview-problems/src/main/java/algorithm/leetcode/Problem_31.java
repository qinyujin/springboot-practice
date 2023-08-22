package algorithm.leetcode;

import java.util.Arrays;

/**
 * @author :覃玉锦
 * @create :2021-03-09 11:55:01
 * 下一个排列
 * https://leetcode-cn.com/problems/next-permutation/
 */
public class Problem_31 {
    public static void main(String[] args) {
        Problem_31 p = new Problem_31();
        int[] num = {1};
        p.nextPermutation(num);
        System.out.println(Arrays.toString(num));
    }

    public void nextPermutation(int[] nums) {
        //思路：可以从后往前找，例如123465它的下一个排列是123546。从后往前查找，当找到6发现6比前一个大，需要交换。
        //交换需要使用6 5 中的较小值。可以先把后部分排序，即排序完后变成5 6 然后交换后return.
        //从后往前
        for (int i = nums.length-1; i > 0; i--) {
            if(nums[i] > nums[i-1]){
                //需要交换
                //把后序排序。排序之后可以轻松获得较小值，并且后序也是最小的，因为增序
                Arrays.sort(nums,i,nums.length);
                for (int j = i; j < nums.length; j++) {
                    if(nums[j] > nums[i-1]){
                        int temp = nums[j];
                        nums[j] = nums[i-1];
                        nums[i-1] = temp;
                        return;
                    }
                }
            }
        }
        //如果没有下一个序列，初始为最小序列。
        Arrays.sort(nums);
    }
}
