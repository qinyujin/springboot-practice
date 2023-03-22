package algorithm.leetcode;

import java.util.Arrays;

/**
 * @author :覃玉锦
 * @create :2023-03-17 14:14:00
 * <p>
 * 螺旋矩阵二
 * https://leetcode.cn/problems/spiral-matrix-ii/
 */
public class Problem_59 {
    public static void main(String[] args) {
        Problem_59 p = new Problem_59();
        int[][] ints = p.generateMatrix(3);
        for (int[] anInt : ints) {
            System.out.println(Arrays.toString(anInt));
        }
    }

    public int[][] generateMatrix(int n) {
        int left = 0, up = 0, right = n - 1, down = n - 1;
        int[][] matrix = new int[n][n];

        int k = 1;
        while (up <= down && left <= right) {
            for (int i = left; i <= right; i++) {
                matrix[up][i] = k++;
            }
            up++;

            for (int i = up; i <= down; i++) {
                matrix[i][right] = k++;
            }
            right--;

            for (int i = right; i >= left; i--) {
                matrix[down][i] = k++;
            }
            down--;

            for (int i = down; i >= up; i--) {
                matrix[i][left] = k++;
            }
            left++;
        }

        return matrix;
    }
}
