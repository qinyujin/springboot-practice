package algorithm.offer;

/**
 * @author :覃玉锦
 * @create :2021-01-22 11:20:00
 * 棋盘递归回溯
 * https://www.nowcoder.com/practice/6e5207314b5241fb83f2329e89fdecc8?tpId=13&tqId=11219&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，但是
 * 不能进入行坐标和列坐标的数位之和大于k的格子。 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。
 * 但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
 */
public class Problem_13 {
    private int rows;//总行数

    private int cols;//总列数

    private int sum;//可以走的步数

    private int threshold;

    private int[][] digitSum;//各个位置的位数和

    public static void main(String[] args) {
        Problem_13 rmr = new Problem_13();
        System.out.println(rmr.movingCount(5, 10, 10));
    }

    /**
     * 思路：先通过一个二维数组digitNum来统计每一个位置的位数和，例如digitNum[35][37] = 3+5+3+7=18
     * 然后在dfs(int[][] visited,int row,int col) 中，使用dfs遍历来解决这个问题。
     * dfs一般步骤：
     * 1、确定边界条件。这里是row、col的边界、是否访问过(visited)、digitNum[row][col] 超过阈值threshold
     * 2、把当前访问的位置的visited设置为true。
     * 3、处理该位置的业务逻辑。这里只需统计步数，让sum++
     * 4、继续往其他方向来递归。这里按照下上右左的顺序。
     * @param threshold
     * @param rows
     * @param cols
     * @return
     */
    public int movingCount(int threshold, int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.threshold = threshold;
        initDigitSum();
        boolean[][] visited = new boolean[rows][cols];
        dfs(visited, 0, 0);
        return sum;
    }

    /**
     * 在走的时候顺便把数量统计了
     *
     * @param visited
     * @param row
     * @param col
     * @return
     */
    private void dfs(boolean[][] visited, int row, int col) {
        //边界条件和退出条件的处理
        if (row < 0 || row >= rows || col < 0 || col >= cols || visited[row][col]) return;
        visited[row][col] = true;
        if (digitSum[row][col] > threshold) return;
        sum++;
        dfs(visited, row + 1, col);
        dfs(visited, row - 1, col);
        dfs(visited, row, col + 1);
        dfs(visited, row, col - 1);
    }

    /**
     * 初始化各位置位数的sum
     */
    private void initDigitSum() {
        int[] digitSumOne = new int[Math.max(rows, cols)];
        //digitSumOne[1] = 1,digitSumOne[11] = 2;
        for (int i = 0; i < digitSumOne.length; i++) {
            int n = i;
            while (n > 0) {
                digitSumOne[i] += n % 10;
                n /= 10;
            }
        }
        this.digitSum = new int[rows][cols];
        for (int i = 0; i < this.rows; i++)
            for (int j = 0; j < this.cols; j++)
                this.digitSum[i][j] = digitSumOne[i] + digitSumOne[j];
    }
}
