package algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author :覃玉锦
 * @create :2021-03-19 20:01:00
 * 柱状图中最大的矩形
 * https://leetcode.cn/problems/largest-rectangle-in-histogram/solution/zhu-zhuang-tu-zhong-zui-da-de-ju-xing-by-leetcode-/
 */
public class Problem_84 {
    public static void main(String[] args) {
        Problem_84 p = new Problem_84();
        int[] heights = {2,1,5,6,2,3};
        System.out.println(p.largestRectangleArea(heights));
    }

    //2,1,5,6,2,3 -> 10
    public int largestRectangleArea(int[] heights) {
        //方法一:暴力解法，遍历每个高，设定i为最高列，向左右扩散，如果遇到高小于当前的就停止。时间o(n^2)
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

        //方法二：单调递增栈的实现方式。原理也是暴力解法的左右扩散，只是通过栈来保存了一部分中间数据，空间换时间 时间o(n) 空间o(n)
        //使用栈的原因是计算面积的过程符合先入后出的特性(结合视频、画图来看),因此可以用栈保存中间的信息。同时通过递增，使得遍历时只
        //需要判断右边低的条件，将暴力解法中的左右同时扩散判断变为只判断一侧了。
        int len = heights.length;
        if(len==0)return 0;
        if(len==1)return heights[0];

        int area = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            //栈出现递减，从后往前计算面积
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]){
                int height = heights[stack.pop()];

                //有相同高度的情况
                while (!stack.isEmpty() && heights[stack.peek()] == height){
                    stack.pop();
                }

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

        //栈里还剩元素的话，说明右边都比栈顶高，因此右扩散可以到底。
        while (!stack.isEmpty()){
            int height = heights[stack.pop()];

            int width;
            //如果是最后一个小的数，即最小，那么宽度应该为全部
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
