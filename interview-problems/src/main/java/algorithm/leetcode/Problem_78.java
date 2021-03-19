package algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-03-12 09:50:00
 * 子集
 * https://leetcode-cn.com/problems/subsets/
 */
public class Problem_78 {
    public static void main(String[] args) {
        Problem_78 p = new Problem_78();
        int[] nums = {1,2,3};
        List<List<Integer>> subsets = p.subsets(nums);
        System.out.println(subsets);
    }

    private List<Integer> temp = new ArrayList<>();
    private List<List<Integer>> res = new ArrayList<>();
    //思路：对于[1,2,3] -> 求子集:[],[1],[2],[1,2],[3].... 一个位置的选择右两种：1、选择当前位置 2、不选择当前位置
    public List<List<Integer>> subsets(int[] nums) {
        backtrace(0,nums);
        return res;
    }

    public void backtrace(int cur,int[] nums){
        if(cur == nums.length){
            res.add(new ArrayList<>(temp));
            return;
        }
        //选择当前元素，那么add之后回溯
        temp.add(nums[cur]);
        backtrace(cur+1,nums);
        //不选择当前元素
        temp.remove(temp.size()-1);
        backtrace(cur+1,nums);
    }
}
