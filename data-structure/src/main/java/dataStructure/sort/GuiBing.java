package dataStructure.sort;

import java.util.Arrays;

/**
 * @author :覃玉锦
 * @create :2020-11-28 14:05:00
 * 归并排序，拆分、分治
 */
public class GuiBing {
    public static void main(String[] args) {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i]=(int)(Math.random()*800000);
        }
        long begin = System.currentTimeMillis();
        Arrays.sort(arr);
        long end = System.currentTimeMillis();
        System.out.println("sort（归并排序优化版本）耗费时间："+(end-begin));
    }
}
