package algorithm.leetcode;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author :覃玉锦
 * @create :2021-03-15 10:53:00
 * 滑动窗口的最大值 --剑指59
 * https://leetcode-cn.com/problems/sliding-window-maximum/
 */
public class Problem_239 {
    public static void main(String[] args) {
        Problem_239 p = new Problem_239();
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] ints = p.maxSlidingWindow(nums, 1);
        System.out.println(Arrays.toString(ints));
    }

    //可以维护一个保持递减的队列，保证队列中元素都在窗口内。这样可以随时拿到窗口内最大值(队首元素)
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < 2) return nums;
        // 双向队列 保存当前窗口最大值的数组位置 保证队列中数组位置的数值按从大到小排序
        LinkedList<Integer> queue = new LinkedList();
        // 结果数组
        int[] result = new int[nums.length - k + 1];
        // 遍历nums数组
        for (int i = 0; i < nums.length; i++) {
            // 保证从大到小 如果前面数小则需要依次弹出，直至满足要求
            while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]) {
                queue.pollLast();
            }
            // 添加当前值对应的数组下标
            queue.addLast(i);
            // 判断当前队列中队首的值是否有效
            if (queue.peek() <= i - k) {
                queue.poll();
            }
            // 当窗口长度为k时 保存当前窗口中最大值
            if (i + 1 >= k) {
                result[i + 1 - k] = nums[queue.peek()];
            }
        }
        return result;
    }
}
