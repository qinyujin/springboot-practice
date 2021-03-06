package algorithm.offer;

/**
 * @author :覃玉锦
 * @create :2021-01-21 10:46:00
 * 二维数组中的查找
 * https://www.nowcoder.com/practice/abc3fe2ce8e146608e868a70efebf62e?tpId=13&tqId=11154&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 题目描述
 * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成
 * 一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * Input:
 * 7,[[1,2,8,9],[2,4,9,12],[4,7,10,13],[6,8,11,15]]
 * Output:
 * true
 */
public class Problem_4 {
    public static void main(String[] args) {
        Problem_4 problem4 = new Problem_4();
        int[][] matrix = new int[][]{
                {1,2,8,9},
                {2,4,9,12},
                {4,7,10,13},
                {6,8,11,15}
        };
        System.out.println(problem4.Find(4, matrix));
        System.out.println(problem4.Find(1, matrix));
        System.out.println(problem4.Find(15, matrix));
        System.out.println(problem4.Find(6, matrix));
        System.out.println(problem4.Find(14, matrix));
    }

    public boolean Find(int target, int [][] array) {
        //解法1 从右上角开始找，小于向左，大于向右。使用二分
        int height=array.length,weight=array[0].length,row=0,col=weight-1;
        if(array==null || height<=0 || weight<=0){
            return false;
        }
        //直接遍历数组？
        //时间复杂度是O(m+n)
        while (row < height && col >= 0){
            if(target == array[row][col]){
                return true;
            }
            else if(target < array[row][col]){
                col--;
            }
            else if(target > array[row][col]){
                row++;
            }
        }
        return false;
    }
}
