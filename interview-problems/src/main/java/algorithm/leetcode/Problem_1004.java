package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2023-03-21 19:02:01
 * <p>
 * 最大连续1的个数Ⅲ
 * https://leetcode.cn/problems/max-consecutive-ones-iii/
 */
public class Problem_1004 {

    public static void main(String[] args) {
        Problem_1004 p = new Problem_1004();
        System.out.println(p.longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2));
    }

    //采用滑动窗口，当窗口内0数量大于k则left右移至0<=k的地方
    public int longestOnes(int[] nums, int k) {
        int left = 0, right = 0, maxLength = 0, zeroCount = 0;
        while (right < nums.length) {
            if (nums[right] == 0) {
                zeroCount++;
            }
            while (zeroCount > k) {
                if (nums[left++] == 0) {
                    zeroCount--;
                }
            }
            maxLength = Math.max(maxLength, right - left + 1);
            right++;
        }
        return maxLength;
    }
}
