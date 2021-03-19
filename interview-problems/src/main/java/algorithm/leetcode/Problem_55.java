package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2021-03-11 10:56:00
 * 跳跃游戏
 * https://leetcode-cn.com/problems/jump-game/
 */
public class Problem_55 {
    public static void main(String[] args) {
        Problem_55 p = new Problem_55();
        int[] nums = {2,3,1,1,4};//3,2,1,0,4
        System.out.println(p.canJump(nums));
    }

    public boolean canJump(int[] nums) {
        //思路：设置一个reach表示当前能到达最远距离，例如2,3,1,1,4 当i=0时，最远距离时到达i=2，即i+arr[i]。如果
        //过程中出现了i > reach则说明无法到达了，返回false
        int reach = 0;
        for (int i = 0; i < nums.length; i++) {
            //需要记录最大距离
            if(i > reach)return false;
            reach = Math.max(reach, i + nums[i]);
        }
        return true;
    }
}
