package algorithm.bytedance;

import java.util.Scanner;

/**
 * @author :覃玉锦
 * @create :2021-03-07 21:17:00
 * 字节3.7后端笔试题
 * 第一题：声哥给你一个正整数数组，求出每个元素和后面第一个值比它大的数字之间的间隔。
 *
 * 暴力解法即可。
 * 例如
 * 5
 * 3 1 2 4 5
 * 输出：
 * 3 1 1 1 -1
 * 解释：3和4的间距是3，1和2的间距是1...5后面没有数/或者后面没有比5大的。
 */
public class Problem1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                //找到第一个大于得
                if(arr[j] > arr[i]){
                    arr[i] = j-i;
                    break;
                }
                if(j==n-1)arr[i]=-1;
            }
        }
        arr[n-1] = -1;
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i]+" ");
        }
    }
}
