package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2023-03-16 12:55:00
 * <p>
 * https://leetcode.cn/problems/single-element-in-a-sorted-array/
 */
public class Problem_540 {
    public static void main(String[] args) {

    }

    //异或的方式时间复杂度o(n) 。本题追求ologn
    public int singleNonDuplicate(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (nums[mid] == nums[mid ^ 1]) l = mid + 1;
            else r = mid;
        }

        return nums[r];
    }
}
