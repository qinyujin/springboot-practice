package algorithm;

import java.util.ArrayList;

/**
 * @author :覃玉锦
 * @create :2021-01-23 21:25:00
 * 顺时针打印矩阵
 * https://www.nowcoder.com/practice/9b4c81a02cd34f76be2659fa0d54342a?tpId=13&tqId=11172&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 */
public class Problem_29 {
    public static void main(String[] args) {
        int[][] matrix = {
                {1,2,3,4,5},
                {6,7,8,9,10},
                {11,12,13,14,15},
                {16,17,18,19,20},
                {21,22,23,24,25}
        };
        System.out.println(new Problem_29().printMatrixnt(matrix));
    }

    //[1, 2, 3, 4, 5, 10, 15, 20, 25, 24, 23, 22, 21, 16, 11, 6, 7, 8, 9, 14, 19, 18, 17, 11, 12, 13]
    public ArrayList<Integer> printMatrixnt(int[][] matrix) {
        ArrayList<Integer> retList = new ArrayList<>(matrix.length);
        //设置上下左右四个边界量
        int left = 0,right = matrix[0].length-1,up = 0,down = matrix.length-1;
        while (left <= right && up <= down){
            //按照顺序遍历
            for (int i = left; i <= right; i++) {
                retList.add(matrix[up][i]);
            }
            for (int i = up+1; i <= down; i++) {
                retList.add(matrix[i][right]);
            }
            if(up!=down){
                for (int i = right-1; i >= left; i--) {
                    retList.add(matrix[down][i]);
                }
            }
            if(left!=right){
                for (int i = down-1; i > up; i--) {
                    retList.add(matrix[i][left]);
                }
            }
            //边界改变
            up++;down--;left++;right--;
        }
        return retList;
    }
}
