package algorithm.leetcode;

import java.util.HashSet;

/**
 * @author :覃玉锦
 * @create :2021-03-19 21:49:01
 * 最长连续序列
 * https://leetcode-cn.com/problems/longest-consecutive-sequence/
 */
public class Problem_128 {
    public static void main(String[] args) {
        Problem_128 p = new Problem_128();
        int[] nums = {100,4,200,1,3,2};
        System.out.println(p.longestConsecutive(nums));
    }

    //可以想办法找到连续序列的第一个数，然后连续找。可以使用set来实现。需要找set中num-1不存在的数则表明num是连续序列第一个数
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        //去重
        for (int num : nums) {
            set.add(num);
        }
        int maxLen = 0;
        for (Integer num : set) {
            //当不包含num-1时，说明num至少是序列第一个数
            if(!set.contains(num-1)){
                int curNum = num;
                int curLen = 1;

                while (set.contains(curNum+1)){
                    curNum = curNum+1;
                    curLen++;
                }
                maxLen = Math.max(maxLen,curLen);
            }
        }
        return maxLen;
    }
}
