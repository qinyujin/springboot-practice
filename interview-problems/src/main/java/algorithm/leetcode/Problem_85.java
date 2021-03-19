package algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author :覃玉锦
 * @create :2021-03-19 21:13:00
 * 最大矩形
 * https://leetcode-cn.com/problems/maximal-rectangle/
 */
public class Problem_85 {
    public static void main(String[] args) {
        Problem_85 p = new Problem_85();
        char[][] matrix = {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'},
        };
        System.out.println(p.maximalRectangle(matrix));
    }

    //https://leetcode-cn.com/problems/maximal-rectangle/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-1-8/
    //这个题可以转换成84题的求面积。把1就看成元素即可看到84的图
    public int maximalRectangle(char[][] matrix) {
        //通过不断的构造heights数组，然后通过84的题解来计算当前面积。
        if(matrix.length==0)return 0;
        int[] heights = new int[matrix[0].length];
        int maxArea = 0;
        for (int i = 0; i < matrix.length; i++) {
            //每一行构造出一个heights，如果为1那么就heights[j]+1
            for (int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j]=='1'){
                    heights[j]+=1;
                }
                else heights[j]=0;
            }
            //通过84题解计算出当前部分的最大面积
            maxArea = Math.max(maxArea,largestRectangleArea(heights));
        }
        return maxArea;
    }

    //84题的解
    public int largestRectangleArea(int[] heights) {
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
