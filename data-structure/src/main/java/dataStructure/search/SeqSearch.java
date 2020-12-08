package dataStructure.search;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author :覃玉锦
 * @create :2020-12-07 20:21:00
 * 线性查找
 */
public class SeqSearch {
    public static void main(String[] args) {
    int[] arr = new int[8];
        for (int i = 0; i < 8; i++) {
            arr[i] = (int)(Math.random()*10000);
        }
        System.out.println("在数组:");
        System.out.println(Arrays.toString(arr));
        System.out.println("中查找值的下标。");
        System.out.println("请输入您要查找的值");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        int index = seqSearch(arr, input);
        if(index!=-1)
        System.out.println("下标:"+index);
        else System.out.println("数组中没有此数！");
    }

    /**
     * 给定一个序列，在里面查找指定值，返回对应下标
     * @param arr
     * @param findVal
     * @return
     */
    public static int seqSearch(int[] arr,int findVal){
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]==findVal){
                return i;
            }
        }
        return -1;
    }
}
