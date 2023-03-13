package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2021-03-12 09:57:00
 * 单词搜索
 * https://leetcode-cn.com/problems/word-search/
 * 二维数组搜索路径
 */
public class Problem_79 {
    public static void main(String[] args) {
        Problem_79 p = new Problem_79();
        char[][] board = new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
//                {'a'}
        };
        String word = "ABCB";
        System.out.println(p.exist(board, word));
    }

    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0))
                    //如果当前位置找到首字符但匹配失败，有可能是首字符位置不对，需要继续寻找
                    if (dfs(board, i, j, word, 0)) return true;
            }
        }
        return false;
    }

    /**
     * @param board 网格
     * @param row   当前对应的行号
     * @param col   当前列号
     * @param word  搜索的目标字符
     * @param index word中的下标位置
     * @return
     */
    public boolean dfs(char[][] board, int row, int col, String word, int index) {
        //全部匹配才算找到
        if (index == word.length()) return true;
        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length) return false;
        if (board[row][col] != word.charAt(index)) return false;

        //保存用于一会还原状态
        char c = board[row][col];
        //改变表示已访问过，之后会走不等于这个条件，这里剪枝就不用重复搜索了，同时这里用原数组保存，节省了额外空间。
        board[row][col] = '0';
        //往四个方向查找，例如先找上，若返回false，则回到这里继续找其他方向
        boolean res =
                dfs(board, row + 1, col, word, index + 1) ||
                        dfs(board, row - 1, col, word, index + 1) ||
                        dfs(board, row, col + 1, word, index + 1) ||
                        dfs(board, row, col - 1, word, index + 1);
        //遍历回来之后需要还原状态
        board[row][col] = c;
        return res;
    }
}
