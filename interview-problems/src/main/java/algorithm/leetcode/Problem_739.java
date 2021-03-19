package algorithm.leetcode;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author :覃玉锦
 * @create :2021-03-19 14:30:00
 * 每日温度
 * https://leetcode-cn.com/problems/daily-temperatures/
 */
public class Problem_739 {
    public static void main(String[] args) {
        Problem_739 p = new Problem_739();
        int[] T = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        int[] ints = p.dailyTemperatures(T);
        System.out.println(Arrays.toString(ints));
    }

    //可以使用单调递减栈。存下标。
    //例如[73, 74, 75, 71, 69, 72, 76, 73]
    //当发现nums[i]比栈顶大，那么栈顶弹出，间距设置为两下标差
    public int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < T.length; i++) {
            while (!stack.isEmpty() && T[i] > T[stack.peek()]){
                res[stack.peek()] = i-stack.pop();
            }
            stack.push(i);
        }
        return res;
    }
}
