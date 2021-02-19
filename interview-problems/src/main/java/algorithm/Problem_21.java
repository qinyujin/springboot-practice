package algorithm;

import java.util.Arrays;

/**
 * @author :覃玉锦
 * @create :2021-01-22 17:56:00
 * 奇数调整到偶数前
 */
public class Problem_21 {
    public static void main(String[] args) {
        Problem_21 ro = new Problem_21();
        int[] arr = {1,2,3,4,5,6};
        ro.reOrderArray(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 奇数在前面，统计奇数/偶数的个数，然后从count开始放相应的数
     * @param array
     */
    public void reOrderArray(int [] array) {
        int count=0;
        int[] clone = array.clone();
        for (int i = 0; i < array.length; i++) {
            if(array[i]%2!=0){
                count++;
            }
        }
        int index = 0;
        for (int i = 0; i < clone.length; i++) {
            if(clone[i]%2!=0){
                array[index++] = clone[i];
            }
            else {
                array[count++] = clone[i];
            }
        }
    }
}
