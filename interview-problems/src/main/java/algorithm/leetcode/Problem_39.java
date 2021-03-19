package algorithm.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-03-10 14:38:00
 * 组合总和
 * https://leetcode-cn.com/problems/combination-sum/
 */
public class Problem_39 {
    public static void main(String[] args) {
        Problem_39 p = new Problem_39();
        int[] arr = {2,3,6,7};
        List<List<Integer>> lists = p.combinationSum(arr, 7);
        System.out.println(lists);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(candidates,0,target,res,new LinkedList<>());
        return res;
    }

    public void dfs(int[] candidates, int begin, int target, List<List<Integer>> res, LinkedList<Integer> path){
        if(target < 0)return;
        if(target==0){
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i < candidates.length; i++) {
            path.addLast(candidates[i]);
            dfs(candidates, i, target-candidates[i], res, path);
            path.removeLast();
        }
    }
}
