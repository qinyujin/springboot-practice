package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2021-03-12 17:28:00
 * 岛屿数量
 * https://leetcode-cn.com/problems/number-of-islands/
 */
public class Problem_200 {
    public static void main(String[] args) {
        Problem_200 p = new Problem_200();
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println(p.numIslands(grid));
    }

    //思路：使用dfs，在原数组中如果找到1，则查找相邻的1，变成0。然后这一整个过程统计一次
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    public void dfs(char[][] grid, int i, int j) {
        //边界或当前位置是0
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0') return;
        grid[i][j] = '0';
        //把0扩散到上下左右所有相连位置
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }
}
