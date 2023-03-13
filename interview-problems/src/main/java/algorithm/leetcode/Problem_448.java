package algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-03-12 20:43:00
 * 找到所有数组中消失的数字
 * https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/
 */
public class Problem_448 {
    public static void main(String[] args) {
        Problem_448 p = new Problem_448();
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println(p.findDisappearedNumbers(nums));
    }

    //查找从1-n中没有出现的数字。思路:原本出现的数字相当于占了一部分的槽位，通过hash变化来计算,下标经过哈希
    //4,3,2,7,8,2,3,1
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        for (int num : nums) {
            //由于nums[i]是[1,n]的范围，如果要对应到下标，就是n-1
            //映射成下标之后，相当于当前n范围内有多少数字占用了哪个槽位是可以确定的，对于这些下标+n，那么已占用槽位的下标符合
            //nums[i]>n。剩余槽位都没操作过，则满足nums[i]<=n.最后的结果则是需要还原i+1，反映射回去
            int index = (num - 1) % n;
            nums[index] += n;
        }
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {
                ret.add(i + 1);
            }
        }
        return ret;
    }
}
