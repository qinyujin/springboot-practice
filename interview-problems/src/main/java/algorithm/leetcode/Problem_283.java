package algorithm.leetcode;

import java.util.Arrays;

/**
 * @author :覃玉锦
 * @create :2021-03-12 20:39:00
 * 移动0
 * https://leetcode-cn.com/problems/move-zeroes/
 */
public class Problem_283 {
    public static void main(String[] args) {
        Problem_283 p =new Problem_283();
        int[] nums = {0,1,0,3,12};
        p.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

    //使用双指针，把非0元素移动到left
    public void moveZeroes(int[] nums) {
        int left = 0,right = 0;
        while (right < nums.length){
            //非0元素移动到left
            if(nums[right]!=0){
                swap(nums,left,right);
                left++;
            }
            right++;
        }
    }

    public void swap(int[] nums,int l,int r){
        int temp = nums[r];
        nums[r] = nums[l];
        nums[l] = temp;
    }
}
