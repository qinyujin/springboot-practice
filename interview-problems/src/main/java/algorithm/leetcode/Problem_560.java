package algorithm.leetcode;

import java.util.HashMap;

/**
 * @author :覃玉锦
 * @create :2021-03-19 11:51:00
 * 和为k的子数组
 * https://leetcode-cn.com/problems/subarray-sum-equals-k/
 */
public class Problem_560 {
    public static void main(String[] args) {
        Problem_560 p = new Problem_560();
        int[] nums = {1,1,1};
        System.out.println(p.subarraySum(nums, 2));
    }

    //可以使用前缀和来判断。前缀和：key:前缀，例如序列2,4 那么4位置的前缀和就是6.value:前缀和的数量
    //pre是当前和，如果pre-k已经在前缀和中出现过，说明当前和到前一次前缀又可以出现一个序列和为k
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer,Integer> mp = new HashMap<>();
        mp.put(0,1);
        int curSum = 0;
        int res = 0;
        for (int num : nums) {
            //当前序列和
            curSum+=num;
            if(mp.containsKey(curSum-k)){
                res+=mp.get(curSum-k);
            }
            mp.put(curSum,mp.getOrDefault(curSum,0)+1);
        }
        return res;
    }
}
