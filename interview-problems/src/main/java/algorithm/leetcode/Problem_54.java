package algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :覃玉锦
 * @create :2023-03-13 11:39:00
 * <p>
 * 旋转数组遍历
 * https://leetcode.cn/problems/spiral-matrix/
 */
public class Problem_54 {

    public static void main(String[] args) {

    }

    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> res = new ArrayList<>();
        int left = 0, up = 0, right = matrix[0].length - 1, down = matrix.length - 1;
        while (true) {
            for (int col = left; col <= right; col++) {
                res.add(matrix[up][col]);
            }
            if (++up > down) {
                break;
            }
            for (int row = up; row <= down; row++) {
                res.add(matrix[row][right]);
            }
            if (--right < left) {
                break;
            }
            for (int col = right; col >= left; col--) {
                res.add(matrix[down][col]);
            }
            if (--down < up) {
                break;
            }
            for (int row = down; row >= up; row--) {
                res.add(matrix[row][left]);
            }
            if (++left > right) {
                break;
            }
        }
        return res;
    }
}
