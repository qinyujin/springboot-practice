package algorithm.bytedance;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * @author :覃玉锦
 * @create :2021-03-07 21:48:00
 * n个人排队成一圈，需要减小相邻的差值。即求出相邻差最大值->最小的情况。
 * https://www.nowcoder.com/questionTerminal/0c16f758949f4d17ab73a9a1ffe7ab9b
 * 那么为了相邻差最小，可以考虑排序，然后从高排到低。又因为是环形，所以可以按照一左一右来排。
 */
public class Problem2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        Arrays.sort(arr, Collections.reverseOrder());
        int[] arr1 = new int[n];
        //前后指针
        int left = 0,right = arr1.length-1;
        int index = 0;
        while (left<right){
            arr1[left++] = arr[index++];
            arr1[right--] = arr[index++];
        }
        if(left==right){
            arr1[left] = arr[index];
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr1.length-1; i++) {
            max = Math.max(max,Math.abs(arr1[i+1]-arr1[i]));
        }
        max = Math.max(max,arr1[0]-arr1[arr1.length-1]);
        System.out.println(max);
    }
}
