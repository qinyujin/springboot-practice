package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2021-03-15 17:12:00
 * 寻找重复数
 * https://leetcode-cn.com/problems/find-the-duplicate-number/
 */
public class Problem_287 {
    public static void main(String[] args) {
        Problem_287 p = new Problem_287();
//        int[] nums = {1,3,4,2,2};
//        int[] nums = {3,1,3,4,2};
//        int[] nums = {1,1};
        int[] nums = {1,1,2};
        System.out.println(p.findDuplicate(nums));
    }

    //使用o(1) 空间来解决。如果不要求o(1) 可以使用哈希来解决。要求o(1) 可以通过交换法，把对应数字交换到对应位置。
    //1,3,4,2,2
    public int findDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int curValue = nums[i];
            if(curValue != i){
                //放到对应下标
                if(curValue == nums[curValue]){
                    return curValue;
                }
                int temp = nums[curValue];
                nums[curValue] = curValue;
                nums[i] = temp;
                i--;
            }
            else {
                continue;
            }
        }
        return 0;
    }
}
