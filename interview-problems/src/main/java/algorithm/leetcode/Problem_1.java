package algorithm.leetcode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author :覃玉锦
 * @create :2021-03-25 09:24:00
 * 两数之和
 * https://leetcode-cn.com/problems/two-sum/
 */
public class Problem_1 {
    public static void main(String[] args) {
        Problem_1 p = new Problem_1();
        int[] nums = {2,7,11,15};
        int[] ints = p.twoSum(nums, 9);
        System.out.println(Arrays.toString(ints)); //[0,1]
    }

    public int[] twoSum(int[] nums, int target) {
        //思路：由于是两数，可以那么可以先取一个，然后判断target-X是否存在
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        int[] arr = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                if (map.get(target - nums[i]) != i) {
                    arr[0] = i;
                    arr[1] = map.get(target - nums[i]);
                    return arr;
                }
            }
        }
        return null;
    }
}
