package algorithm.leetcode;

import java.util.Arrays;

/**
 * @author :覃玉锦
 * @create :2021-03-10 15:45:00
 * 旋转图像
 * https://leetcode.cn/problems/rotate-image/solution/xuan-zhuan-tu-xiang-by-leetcode-solution-vu3m/
 */
public class Problem_48 {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Problem_48 p = new Problem_48();
//        p.rotate(matrix);
        p.rotateWithMathMethod(matrix);
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

    //思路：先对角线对称旋转再左右旋转。时间:时间o(2*n^2)
    public void rotate(int[][] matrix) {
        //对角线旋转
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        //左右旋转
        int len = matrix[0].length;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < len / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][len - j - 1];
                matrix[i][len - j - 1] = temp;
            }
        }
    }

    //时间o(n^2)
    public void rotateWithMathMethod(int[][] matrix) {
        int n = matrix.length;
        //对角线到中心的元素数量
        for (int i = 0; i < n / 2; i++) {
            //从外到内旋转每一层要处理的数量,从i开始.[i,(n-1)-i],表示外圈层处理过，在此处理内圈
            for (int j = i; j < n - 1 - i; j++) {
                //旋转是四个元素的交换.    下标分别是:[i][j]  [j][n-1-i]  [n-1-i][n-1-j]    [n-1-j][i] 可以按照每一层是怎么运转的来分析下标
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = temp;
            }
        }
    }
}
