package algorithm;

import java.util.Arrays;

/**
 * @author :覃玉锦
 * @create :2021-01-21 20:59:00
 */
public class Practice {
    public static void main(String[] args) {
        //22 67 62 21 7 29 15 59 49 51
        Practice practice =  new Practice();
        int[] arr = {4,3,8,7,6,9};
        practice.reOrderArray(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void reOrderArray(int [] array) {
        int oddCount = 0;
        for (int i : array) {
            if(i%2!=0)oddCount++;
        }
        int[] clone = array.clone();
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            if(clone[i]%2!=0){
                array[index++] = clone[i];
            }
            else {
                array[oddCount++] = clone[i];
            }
        }
    }
}
