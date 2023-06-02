package algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author :覃玉锦
 * @create :2023-03-16 13:58:01
 * 组合
 * https://leetcode.cn/problems/combinations/
 */
public class Problem_77 {
    public static void main(String[] args) {
        Problem_77 p = new Problem_77();
        System.out.println(p.combine(4, 2));
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayDeque<>(), n, k, 1);
        return res;
    }

    public void dfs(List<List<Integer>> res, Deque<Integer> path, int n, int k, int begin) {
        if (k == path.size()) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i <= n; i++) {
            path.addLast(i);
            dfs(res, path, n, k, i + 1);
            path.removeLast();
        }
    }
}
