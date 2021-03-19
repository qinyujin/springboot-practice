package algorithm.leetcode;

import java.util.Arrays;

/**
 * @author :覃玉锦
 * @create :2021-03-11 15:03:00
 * 颜色分类
 * https://leetcode-cn.com/problems/sort-colors/
 *
 * 例如 2,0,2,1,1,0 进行原地排序。
 */
public class Problem_75 {
    public static void main(String[] args) {
        Problem_75 p = new Problem_75();
        int[] nums = {2,0,2,1,1,0};
        p.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }

    //思路：双指针法，借助了快排的交换思想，有点类似于1是pivot。这里设置p0，p2两个指针，分别存放0的位置和2的位置。
    //2,0,2,1,1,0 -> 0,0,1,1,2,2
    //可以设置p0，i，p2。p0表示0的右位置。p2表示2的左位置。
    //可以定义区间如下：
    //[0,p0) == 0
    //[p0,i) == 1
    //(p2,n-1) == 2
    //可以理解为p0是0和1的界限，在1上落实。p2也是界限，也在1上落实
    public void sortColors(int[] nums) {
        int p0=0,i=0,p2=nums.length-1;
        //如果i大于p2那么说明p2已经排好了
        while (i <= p2){
            if(nums[i] == 0){
                //把0换到p0位置
                swap(nums,i,p0);
                p0++;
                i++;
            }
            else if(nums[i] == 1){
                i++;
            }
            else {
             //把2放到p2位置,这里不选择i++，因为有可能p2的元素交换到i位置，i位置不对，需要再次交换，所以下一次循环i在原地
             swap(nums,i,p2);
             p2--;
            }
        }
    }

    public void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
