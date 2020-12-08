package dataStructure.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :覃玉锦
 * @create :2020-12-07 20:27:00
 * 二分查找，必须是有序数列。
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1,8,10,89,1000,1000,1000,1234};
//        int resIndex = binarySearch(arr, 0, arr.length - 1, 11);
        List<Integer> resIndexList = binarySearch2(arr, 0, arr.length - 1, 1000);
        System.out.println(resIndexList);
    }

    /**
     * 递归写法
     * @param arr 指定数组
     * @param left 左索引
     * @param right 右索引
     * @param findVal 查找的值
     * @return
     */
    public static int binarySearch(int[] arr,int left,int right,int findVal){
        int mid = (left+right)/2;
        int midVal = arr[mid];
        if(left>right) return -1;

        if(findVal > midVal){
            return binarySearch(arr, mid+1,right,findVal);
        }
        else if(findVal < midVal){
            return binarySearch(arr,left,mid-1,findVal);
        }
        else return mid;
    }

    /**
     * 二分查找，可以查找相邻相同的数，由于是有序数列，所以是都能查到
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return
     */
    public static List<Integer> binarySearch2(int[] arr,int left,int right,int findVal){
        int mid = (left+right)/2;
        int midVal = arr[mid];
        if(left>right) return new ArrayList<>();

        if(findVal > midVal){
            return binarySearch2(arr, mid+1,right,findVal);
        }
        else if(findVal < midVal){
            return binarySearch2(arr,left,mid-1,findVal);
        }
        else {
            int temp = mid-1;
            List<Integer> resultList = new ArrayList<>();
            while (true){
                if(temp<0 || arr[temp]!=findVal){
                    break;
                }
                resultList.add(temp);
                temp--;
            }
            resultList.add(mid);
            temp = mid+1;
            while (true){
                if(temp>arr.length-1 || arr[temp]!=findVal){
                    break;
                }
                resultList.add(temp);
                temp++;
            }
            return resultList;
        }
    }
}
