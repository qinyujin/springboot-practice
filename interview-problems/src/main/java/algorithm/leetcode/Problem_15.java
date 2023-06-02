package algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-03-03 15:55:01
 * 三数之和
 * https://leetcode-cn.com/problems/3sum/solution/
 */
public class Problem_15 {
    public static void main(String[] args) {
        Problem_15 p = new Problem_15();
        int[] nums = {-1, 0, 1, 2, -1, -4, -2, -3, 3, 0, 4};
        System.out.println(p.threeSum(nums));
    }

    /**
     * 先对数组排序，然后遍历每一个数i，从i+1至n(数组长度)-1的元素中双指针遍历每一个元素，从左右两边向中间查询，查找到和为0的就记录下来.题目要求三元组不重复，则需要在
     * [i+1,n]的区间中注意去重处理
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length <= 2) return new ArrayList<>();
        List<List<Integer>> lists = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            //如果nums[i] 已经大于0了，那么[i,n]的范围由于增序，不会再出现
            if (nums[i] > 0) return lists;
            //在下面的while循环中已经把包含nums[i]元素的三元组找完了，此处需要去重
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            //通过求和：nums[i]+nums[l]+nums[r] 来判断
            int l = i + 1, r = nums.length - 1;
            //在区间[i+1,n-1]之间来寻找剩余的两个数
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    //如果为0，那么当前三元组添加到结果中去，同时为了不重复，需要把
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[l]);
                    list.add(nums[r]);
                    lists.add(list);
                    //题目要求不含重复的三元组，那么如果下一个数相同的话要跳过的
                    while (l < r && nums[l] == nums[l + 1]) l++;
                    while (l < r && nums[r] == nums[r - 1]) r--;
                    l++;
                    r--;
                } else if (sum < 0) {
                    l++;
                } else if (sum > 0) {
                    r--;
                }
            }
        }
        return lists;
    }
}
