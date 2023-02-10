package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2021-03-09 12:52:00
 * 搜索旋转排序数组
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
 */
public class Problem_33 {
    public static void main(String[] args) {
        Problem_33 p = new Problem_33();
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(p.search(nums, 3));
    }

    //二分查找需要在有序数组上操作，由于旋转的特性，可以找到arr[mid],分为左右两部分，必定有一部分是递增的，即可以使用二分
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[0] <= nums[mid]) {
                //左区间递增[0,mid]，在左边[0,mid)使用二分来查找，递增的话也可以判断target是否在此区间中。若target不在区间中，继续调整查找区间.要处理下临界情况，target=nums[0]
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                //(mid,nums.length-1],临界:target=nums[nums.length-1]
                if (nums[mid] < target && target <= nums[nums.length - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }
}
