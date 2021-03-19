package juc.practice;

import java.util.Arrays;

/**
 * @author :覃玉锦
 * @create :2021-01-04 11:56:00
 */
public class Practice {
    public static void main(String[] args) {
        int[] arr = {2,4,1,3,5,16,21,48};
        quicklySort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
        //sizeCtl : 自旋设置值 0 ->(cas) -1 (initial)-> 12(threshold)
    }

    public static void quicklySort(int[] arr,int left,int right){
        //思路：使得mid的左边全小，右边全大。
        //通过找左边第一个大和右边第一个小，交换。找到坐标后如果左右相等，break。交换完之后如果
        //左等于mid，r--右边同理。如果移动后l==r，那么l++ r--同时。
        int l = left,r = right;
        int mid =

                (left+right)/2;
        while (l<r){
            while (arr[l] < arr[mid])l++;
            while (arr[r] > arr[mid])r--;
            if(l>=r)break;
            int temp = arr[r];
            arr[r] = arr[l];
            arr[l] = temp;
            if(arr[l]==arr[mid])r--;
            if(arr[r]==arr[mid])l--;
        }
        if(l==r){
            l++;
            r--;
        }
        if(left<r)quicklySort(arr,left,r);
        if(right>l)quicklySort(arr,l,right);
    }
}