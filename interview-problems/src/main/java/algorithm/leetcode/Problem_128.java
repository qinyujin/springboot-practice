package algorithm.leetcode;

import java.util.HashSet;

/**
 * @author :覃玉锦
 * @create :2021-03-19 21:49:00
 * 最长连续序列
 * https://leetcode-cn.com/problems/longest-consecutive-sequence/
 */
public class Problem_128 {
    public static void main(String[] args) {
        Problem_128 p = new Problem_128();
        int[] nums = {100,4,200,1,3,2};
        System.out.println(p.longestConsecutive(nums));
    }

    //可以使用哈希，如果一个数的n-1存在于set之中，跳过。直到找到n-1不存在于集合中，那么说明这个数至少是
    //递增的第一个数，然后一直找它的n+1存在否，同时统计长度
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
