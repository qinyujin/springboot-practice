package algorithm.leetcode;

import java.util.Arrays;

/**
 * @author :覃玉锦
 * @create :2021-03-09 17:50:00
 * 在排序数组中查找元素的第一个和最后一个位置
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 */
public class Problem_34 {
    public static void main(String[] args) {
        Problem_34 p = new Problem_34();
        int[] nums = {};
        int[] ints = p.searchRange(nums, 0);
        System.out.println(Arrays.toString(ints));
    }

    public int[] searchRange(int[] nums, int target) {
        //思路：排序的数组可以使用二分查找到target之后，再次统计即可
        int l = 0, r = nums.length - 1;
        int low = -1, hight = -1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] < target) {
                l = mid + 1;
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                //如果相等，那么找到最小下标
                while (mid - 1 >= 0 && nums[mid - 1] == nums[mid]) mid--;
                low = mid;
                break;
            }
        }
        //寻找高位
        if (low != -1) {
            for (int i = low; i < nums.length; i++) {
                if (nums[i] != target) break;
                hight = i;
            }
        }
        return new int[]{low, hight};
    }
}
