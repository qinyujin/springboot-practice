package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2021-03-03 15:13:00
 * 盛水最多的容器
 * https://leetcode-cn.com/problems/container-with-most-water/
 */
public class Problem_11 {
    public static void main(String[] args) {
        Problem_11 p = new Problem_11();
        int[] height = {1,2,1};
        int i = p.maxArea(height);
        System.out.println(i);
    }

    /**
     * 可以使用双指针从前后来往中间找。然后在这个过程中记录最大面积。移动哪条边？移动两条中较短的。因为贪心思想不可能
     * 去掉较长边来减少面积。
     * 时间复杂度是on 因为遍历一遍
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int maxArea = Integer.MIN_VALUE;
        int left = 0,right = height.length-1;
        while (left < right){
            //面积的计算方法：较短高乘上两边间距
            int area = Math.min(height[left],height[right]) * (right-left);
            maxArea = Math.max(maxArea,area);
            //移动较小的边
            if(height[left] < height[right])left++;
            else right--;
        }
        return maxArea;
    }
}
