package algorithm.leetcode;

import java.util.Arrays;

/**
 * @author :覃玉锦
 * @create :2021-03-15 10:41:00
 * 除自身以外数组的乘积
 * https://leetcode-cn.com/problems/product-of-array-except-self/solution/chu-zi-shen-yi-wai-shu-zu-de-cheng-ji-by-leetcode-/
 */
public class Problem_238 {
    public static void main(String[] args) {
        Problem_238 p =new Problem_238();
        int[] nums = {1,2,3,4};
        int[] ints = p.productExceptSelf(nums);
        System.out.println(Arrays.toString(ints));
    }

    //1,2,3,4   可以把问题分解成求前缀积和后缀积，例如2的前缀就是1，后缀是3，4.1没有前缀，可以把前缀看成1，因为1*后缀
    //等于后缀。
    //那么整个求解就可以通过先遍历前缀求出前缀积，再从后往前求后缀，然后相乘
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        for (int i = 0,product = 1; i < nums.length;i++) {
            result[i] = product;
            product*=nums[i];
        }
        for (int i = nums.length-1,product = 1; i >= 0;i--) {
            //前缀乘后缀
            result[i]*=product;
            product*=nums[i];
        }
        return result;
    }
}
