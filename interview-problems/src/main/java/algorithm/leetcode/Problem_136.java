package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2021-03-12 14:13:00
 * 只出现一次的数字
 * https://leetcode-cn.com/problems/single-number/
 */
public class Problem_136 {
    public static void main(String[] args) {
        Problem_136 p = new Problem_136();
        int[] nums = {4, 1, 2, 1, 2};
        System.out.println(p.singleNumber(nums));
    }

    //思路：异或,位运算，该位不同则为1，相同则为0.性质：相同数异或结果为0，不同数异或结果为1.由于题目是x元素只出现一次，其他均
    // 出现两次。因此出现偶数次的数字经过异或运算后结果为0，0再与奇数元素异或，还是为x.
    public int singleNumber(int[] nums) {
        int num = nums[0];
        if (nums.length > 1) {
            for (int i = 1; i < nums.length; i++) {
                num ^= nums[i];
            }
        }
        return num;
    }
}
