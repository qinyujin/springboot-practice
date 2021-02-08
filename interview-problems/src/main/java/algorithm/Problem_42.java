package algorithm;

/**
 * @author :覃玉锦
 * @create :2021-01-27 20:16:00
 * 子数组最大和
 * https://www.nowcoder.com/practice/459bd355da1549fa8a49e350bf3df484?tpId=13&tqId=11183&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 输入一个整型数组，数组里有正数也有负数。数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。要求时间复杂度为 O(n).
 *
 * 思路：一个sum遍历时求和，只求正数和，一个Max保存过程中的最大值
 */
public class Problem_42 {
    public static void main(String[] args) {
        int[] arr = {1,-2,3,10,-4,7,2,-5};
        System.out.println(new Problem_42().FindGreatestSumOfSubArray(arr));
    }

    public int FindGreatestSumOfSubArray(int[] array) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int val : array) {
            sum = sum <= 0 ? val : sum+val;
            max = Math.max(max,sum);
        }
        return max;
    }
}
