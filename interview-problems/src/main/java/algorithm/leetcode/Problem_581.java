package algorithm.leetcode;

import java.util.Stack;

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

    //2,6,4,8,10,9,15 ->5 因为需要排序[6, 4, 8, 10, 9]
    //可以通过排序之后，找到两个数组中不同位置的最大和最小值。
    //2,6,4,8,10,9,15 sort-> 2,4,6,8,9,10,15  不同位置是 1,2 4,5    那么结果就是5-1+1 时间复杂度是nlog(n)
    //可以使用栈来记录不同位置的最大最小值，可以优化成o(n)
    public int findUnsortedSubarray(int[] nums) {
        int l = nums.length-1,r = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            //保证栈是单调递增，因此就可以找到第一个下降点。例如2,6,4 stack{2,6} 符合条件，弹出6的下标1作为l
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]){
                l = Math.min(l,stack.pop());
            }
            stack.push(i);
        }
        stack.clear();
        for (int i = nums.length - 1; i >= 0; i--) {
            //通过单调递减的栈来得到不同位置的最大下标
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]){
                r = Math.max(r,stack.pop());
            }
            stack.push(i);
        }
        return r-l <= 0 ? 0 : r-l+1;
    }
}
