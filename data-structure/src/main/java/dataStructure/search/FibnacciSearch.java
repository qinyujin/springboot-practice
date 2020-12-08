package dataStructure.search;

import java.util.Arrays;

/**
 * @author :覃玉锦
 * @create :2020-12-08 09:50:00
 * 斐波那契查找，基于二分
 */
public class FibnacciSearch {
    private static int maxSize = 20;
    public static void main(String[] args) {
        int[] arr = {1,8,10,89,1000,1234};
        int index = fibnacciSearch(arr, 1234);
        System.out.println("下标："+index);
    }

    /**
     * 利用斐波那契数列进行查找，如果整个数组可以用f[k]-1来表示，那就可以分成两个部分-》f[k-1]-1和f[k-2]-1，mid的公式为mid = low+f[k-1]-1，
     * 由于原来数组长度可能没有f[k]-1大，所以要新建一个临时数组，用于拓展长度
     *
     * @param arr
     * @param key
     * @return
     */
    public static int fibnacciSearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int k = 0;
        int[] f = fib();

        //找到k的值
        while (high > f[k] - 1) {
            k++;
        }
        //拓展原始数组
        int[] temp = Arrays.copyOf(arr, f[k]);
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }
        while (low <= high) {
            int mid = low + f[k - 1] - 1;
            //小于找左边，结合图片可知k-1为左边部分
            if (key < temp[mid]) {
                high = mid - 1;
                k -= 1;
            } else if (key > temp[mid]) {
                low = mid + 1;
                k -= 2;
            } else {
                //有可能超出原有数组最大，因为是填充的
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }

    public static int[] fib(){
        int[] fibArr = new int[maxSize];
        fibArr[0] = fibArr[1] = 1;
        for (int i =2; i < maxSize; i++) {
            fibArr[i] = fibArr[i-1]+fibArr[i-2];
        }
        return fibArr;
    }
}
