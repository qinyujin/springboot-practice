package algorithm.leetcode;

import java.util.HashMap;

/**
 * @author :覃玉锦
 * @create :2023-03-17 11:17:00
 * 四数相加Ⅱ
 * <p>
 * https://leetcode.cn/problems/4sum-ii/
 */
public class Problem_454 {
    public static void main(String[] args) {
        Problem_454 p = new Problem_454();
        System.out.println(p.fourSumCount(new int[]{1, 2}, new int[]{-2, -1}, new int[]{-1, -2}, new int[]{0, 2}));
    }

    //四个数组，可以分为前两个 | 后两个 给分成两部分，保证这两部分和为0就行了。
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        //nums1[i] + nums2[j]  ->   count()
        HashMap<Integer, Integer> numCount = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                numCount.put(nums1[i] + nums2[j], numCount.getOrDefault(nums1[i] + nums2[j], 0) + 1);
            }
        }
        int res = 0;
        for (int i = 0; i < nums3.length; i++) {
            for (int j = 0; j < nums4.length; j++) {
                if (numCount.containsKey(-nums3[i] - nums4[j])) {
                    res += numCount.get(-nums3[i] - nums4[j]);
                }
            }
        }
        return res;
    }
}
