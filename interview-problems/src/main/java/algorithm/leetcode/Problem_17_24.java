package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2023-03-15 11:29:00
 *
 * 最大子矩阵
 * https://leetcode.cn/problems/max-submatrix-lcci/
 */
public class Problem_17_24 {

    public static void main(String[] args) {
        Problem_17_24 p = new Problem_17_24();
        int[] res = p.getMaxMatrix(new int[][]{
                {-1, 0},
                {0, -1}
        });
        for (int re : res) {
            System.out.print(re + " ");
        }
    }

    //列求和就可以转化成最大连续序列问题，矩阵可以是1*1 2*1等，因此采用递增列和的方式来计算
    public int[] getMaxMatrix(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        int[] b = new int[m];
        int[] res = new int[4];
        int maxSum = Integer.MIN_VALUE;
        int leftRow = 0, leftCol = 0;

        for (int i = 0; i < n; i++) {
            //init
            for (int t = 0; t < m; t++) {
                b[t] = 0;
            }
            for (int j = i; j < n; j++) {
                int sum = 0;
                for (int k = 0; k < m; k++) {
                    //对应列的和
                    b[k] += matrix[j][k];

                    if (sum > 0) {
                        sum += b[k];
                    } else {
                        sum = b[k];
                        //i是因为矩阵左上角是从i开始往下扫
                        leftRow = i;
                        leftCol = k;
                    }

                    if (sum > maxSum) {
                        maxSum = sum;
                        //记录下标
                        //左上的下标
                        res[0] = leftRow;
                        res[1] = leftCol;

                        res[2] = j;
                        res[3] = k;
                    }
                }
            }
        }

        return res;
    }
}
