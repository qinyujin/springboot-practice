package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2021-03-10 15:06:00
 * 接雨水
 * https://leetcode-cn.com/problems/trapping-rain-water/
 */
public class Problem_42 {
    public static void main(String[] args) {
        Problem_42 p = new Problem_42();
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        int trap = p.trap(height);
        System.out.println(trap);
    }

    //采用双指针，从左和右一起来遍历，当左高小于右高，那么记录左边的最高，如果发现下跌那么就求和，值为leftMax-height[left]
    //右边同理
    public int trap(int[] height) {
        int left=0,right=height.length-1;
        int leftMax = 0,rightMax = 0,ans=0;
        while (left < right){
            //如果左高小于右高说明可以递增来判断
            if(height[left] < height[right]){
                if(leftMax <= height[left])leftMax = height[left];
                else ans += (leftMax - height[left]);
                left++;
            }
            //左高大于右高，从右往左递增
            else {
                if(rightMax <= height[right])rightMax = height[right];
                else ans += (rightMax - height[right]);
                right--;
            }
        }
        return ans;
    }
}
