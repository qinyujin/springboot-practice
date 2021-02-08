package algorithm;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author :覃玉锦
 * @create :2021-01-28 19:09:00
 * 和为s的两个数字
 * https://www.nowcoder.com/practice/390da4f7a00f44bea7c2f3d19491311b?tpId=13&tqId=11195&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。
 */
public class Problem_57_1 {
    public static void main(String[] args) {
        int[] array = {1,2,4,7,11,15};
        int sum = 15;
        System.out.println(new Problem_57_1().FindNumbersWithSum(array, sum));
    }
    //由于排序好了，并且只用找两个数，那么要寻找就可以用两个指针，同时从前和后寻找
    public ArrayList<Integer> FindNumbersWithSum(int [] array, int sum) {
        int i = 0,j=array.length-1;
        int target;
        while (i<j){
            target = array[i]+array[j];
            //从前后一起找，如果值大了，说明目标在左边，减少大值
            if(target > sum){
                j--;
            }
            else if(target < sum){
                i++;
            }
            else{
                return new ArrayList<>(Arrays.asList(array[i],array[j]));
            }
        }
        return new ArrayList<>();
    }
}
