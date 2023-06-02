package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2023-03-16 12:55:00
 * 有序数组中的单一元素
 *
 * https://leetcode.cn/problems/single-element-in-a-sorted-array/
 */
public class Problem_540 {
    public static void main(String[] args) {

    }

    //异或的方式时间复杂度o(n) 。本题追求ologn.
    //1、假设全部是只出现两次，那么一对数字第一个一定是偶数下标，第二个一定是奇数
    //2、插入一个单独出现的数字x之后，x之前的还是可以保证1的性质，x之后的则正好反转过来
    public int singleNonDuplicate(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = (l + r) /2;
            //偶数
            if (mid % 2 == 0) {
                //说明当前在前半段，移动l
                if (mid + 1 < n && nums[mid] == nums[mid + 1]) l = mid + 1;
                //否则是在后半段
                else r = mid;
            } else {
                if (mid - 1 >= 0 && nums[mid - 1] == nums[mid]) l = mid + 1;
                else r = mid;
            }
        }
        return nums[r];
    }
}
