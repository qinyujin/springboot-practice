package algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author :覃玉锦
 * @create :2021-03-19 20:01:00
 * 柱状图中最大的矩形
 * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
 */
public class Problem_84 {
    public static void main(String[] args) {
        Problem_84 p = new Problem_84();
        int[] heights = {2,1,5,6,2,3};
        System.out.println(p.largestRectangleArea(heights));
    }

    //2,1,5,6,2,3 -> 10
    public int largestRectangleArea(int[] heights) {
        //方法一:暴力解法，遍历每个高，然后向左右扩散，如果遇到高小于当前的就停止
        /*int max = 0;
        int l = 0,r = 0;
        for (int i = 0; i < heights.length; i++) {
            l = i-1;
            r=i+1;
            while (l>=0 && heights[l] >= heights[i])l--;
            while (r<heights.length && heights[r] >= heights[i])r++;
            //l<i r<i -1 1   -1 6   1 1
            max = Math.max((r-l-1) * heights[i],max);
        }
        return max;*/

        //方法二：通过单调递增的栈来实现
        int len = heights.length;
        if(len==0)return 0;
        if(len==1)return heights[0];

        int area = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]){
                int height = heights[stack.pop()];

                int width;
                if(stack.isEmpty()){
                    //单调递增的栈，那么最后保留的肯定左边比栈顶小
                    width = i;
                }else {
                    width = i-stack.peek()-1;
                }
                area = Math.max(area,width*height);
            }
            stack.push(i);
        }

        while (!stack.isEmpty()){
            int height = heights[stack.pop()];

            int width;
            //根据栈单调递增的性质推导出width
            if(stack.isEmpty()){
                width = len;
            }else {
                width = len-stack.peek()-1;
            }
            area = Math.max(area,width*height);
        }
        return area;
    }
}
