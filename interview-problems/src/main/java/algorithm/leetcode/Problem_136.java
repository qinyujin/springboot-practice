package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2021-03-12 14:13:00
 * 只出现一次的数字
 * https://leetcode-cn.com/problems/single-number/
 */
public class Problem_136 {
    public static void main(String[] args) {
        Problem_136 p =new Problem_136();
        int[] nums = {4,1,2,1,2};
        System.out.println(p.singleNumber(nums));
    }

    //思路：使用异或^可以抵消成对数字，剩余的数字就是异或结果。如果使用0来异或就可以得到原数字
    public int singleNumber(int[] nums) {
        int diff = 0;
        for (int num : nums) {
            diff ^=num;
        }
        return diff;
    }
}
