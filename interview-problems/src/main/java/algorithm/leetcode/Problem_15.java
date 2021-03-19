package algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-03-03 15:55:00
 * 三数之和
 * https://leetcode-cn.com/problems/3sum/solution/
 */
public class Problem_15 {
    public static void main(String[] args) {
        Problem_15 p = new Problem_15();
        int[] nums ={-1,0,1,2,-1,-4};
        List<List<Integer>> lists = p.threeSum(nums);
        System.out.println();
    }

    /**
     * 思路是选中一个元素i后从i+1到n-1的范围内通过双指针来查找。
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
            //当前算法相当于是把含有nums[i] 的所有组成0的list全部找齐。因此下一次要寻找需要跳过nums[i]避免重复
            if(i>0 && nums[i] == nums[i-1])continue;
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
                    //避免重复的位，并且移动l，r。保证固定nums[i]的情况下把含nums[i]的全部组合都找到。
                    while (l<r && nums[l] == nums[l+1])l++;
                    while (l<r && nums[r] == nums[r-1])r--;
                    l++;
                    r--;
                }
                else if(sum < 0){
                    l++;
                }
                else if(sum > 0){
                    r--;
                }
            }
        }
        return lists;
    }
}
