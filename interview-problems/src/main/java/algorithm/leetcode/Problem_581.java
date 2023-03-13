package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2021-03-19 12:50:00
 * 最短无序连续子数组
 * https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/
 */
public class Problem_581 {
    public static void main(String[] args) {
        Problem_581 p = new Problem_581();
        int[] nums = {1}; //5
        System.out.println(p.findUnsortedSubarray(nums));
    }

    //一次遍历 时间o(n).只需要确定中间的左右两指针位置即可，如何确定指针位置：以右指针为例，找到最大值，如果右边比最大小，更新右
    //指针。左指针相反
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int maxn = Integer.MIN_VALUE, right = -1;
        int minn = Integer.MAX_VALUE, left = -1;
        for (int i = 0; i < nums.length; i++) {
            if (maxn > nums[i]) {
                right = i;
            } else {
                maxn = nums[i];
            }

            //left
            if (minn < nums[n - 1 - i]) {
                left = n - 1 - i;
            } else {
                minn = nums[n - 1 - i];
            }
        }

        return right == -1 ? 0 : right - left + 1;
    }
}
