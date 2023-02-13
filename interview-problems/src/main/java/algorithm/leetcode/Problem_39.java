package algorithm.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-03-10 14:38:00
 * 组合总和
 * https://leetcode.cn/problems/combination-sum/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-m-2/
 */
public class Problem_39 {
    public static void main(String[] args) {
        Problem_39 p = new Problem_39();
        int[] arr = {2, 3, 6, 7};
        List<List<Integer>> lists = p.combinationSum(arr, 7);
        System.out.println(lists);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(candidates, 0, target, res, new LinkedList<>());
        return res;
    }

    /**
     * 通过构建树来查找，子节点值为0则路径为找到的集合.通过减来构建树。建议画图来看
     *
     * @param candidates 数组
     * @param begin      题目要求不按顺序，因此要按照某种顺序来查找，规定从begin开始，即使用过的元素不再下一次查找中重复使用。可看作是剪枝
     * @param target
     * @param res
     * @param path       存放走过的路径
     */
    public void dfs(int[] candidates, int begin, int target, List<List<Integer>> res, LinkedList<Integer> path) {
        if (target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i < candidates.length; i++) {
            path.addLast(candidates[i]);
            dfs(candidates, i, target - candidates[i], res, path);
            path.removeLast();
        }
    }
}
