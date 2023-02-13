package algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-03-10 15:18:00
 * 全排列
 * https://leetcode-cn.com/problems/permutations/
 */
public class Problem_46 {
    public static void main(String[] args) {
        Problem_46 p = new Problem_46();
        int[] nums = {1, 2, 3};
        List<List<Integer>> permute = p.permute(nums);
        System.out.println(permute);
    }

    //回溯
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        backtrace(nums, new ArrayList<>(), new boolean[nums.length], lists);
        return lists;
    }

    public void backtrace(int[] nums, List<Integer> list, boolean[] visited, List<List<Integer>> lists) {
        if (list.size() == nums.length) {
            lists.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                list.add(nums[i]);
                backtrace(nums, list, visited, lists);
                visited[i] = false;
                list.remove(list.size() - 1);
            }
        }
    }
}
