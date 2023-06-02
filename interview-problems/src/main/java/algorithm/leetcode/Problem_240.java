package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2021-03-15 14:05:01
 * 搜索二维矩阵Ⅱ
 * https://leetcode-cn.com/problems/search-a-2d-matrix-ii/
 */
public class Problem_240 {
    public static void main(String[] args) {
        Problem_240 p = new Problem_240();
        /*int[][] matrix = {
                {1,4,7,11,15},
                {2,5,8,12,19},
                {3,6,9,16,22},
                {10,13,14,17,24},
                {18,21,23,26,30}
        };*/
        int[][] matrix = {
                {1,1}
        };
//        System.out.println(p.searchMatrix(matrix, 5));
//        System.out.println(p.searchMatrix(matrix, 20));
        System.out.println(p.searchMatrix(matrix, 2));
    }

    //特点：左->右 升序 上->下 升序
    //可以使用二分，初始位置在右上，如果大往下走，小往左走
    public boolean searchMatrix(int[][] matrix, int target) {
        int col = matrix[0].length-1,row = 0;
        while (col >= 0 && row < matrix.length){
            if(matrix[row][col] == target)return true;
            else if(matrix[row][col] > target)col--;
            else row++;
        }
        return false;
    }
}
