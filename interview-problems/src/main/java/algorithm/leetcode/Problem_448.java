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
        int[] nums = {4,3,2,7,8,2,3,1};
        System.out.println(p.findDisappearedNumbers(nums));
    }

    //查找从1-n中没有出现的数字。思路:可以利用nums作为哈希表，把原始数据全部增加到n以上
    //4,3,2,7,8,2,3,1
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n =nums.length;
        for (int num : nums) {
            //为什么是num-1和为什么取余：例如1的下标哈希值应该对应是0.所以需要减一。为什么取余?像这里2出现多次，那么如果
            //加起来超过了n，就需要取余来确定原始的值。这样数组里出现的数字经过哈希映射，数组中对应下标的值都超过了n
            int x = (num-1)%n;
            nums[x] += n;
        }
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if(nums[i] <= n){
                ret.add(i+1);
            }
        }
        return ret;
    }
}
