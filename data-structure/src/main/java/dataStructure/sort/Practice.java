package dataStructure.sort;

import java.util.Arrays;

/**
 * @author :覃玉锦
 * @create :2020-11-28 21:35:00
 * 练习各种排序代码
 * n较小时选择：冒泡交换选择
 * 大部分排序好时选择：插入
 * n较大时选择：快速归并堆
 */
public class Practice {
    public static void main(String[] args) {
        int[] arr = {99,35,93,21,8,58,80,68};
        System.out.println("原始数组："+Arrays.toString(arr));
        int[] bubbleArr = bubbleSort(arr);
        System.out.println("冒泡："+Arrays.toString(bubbleArr));
        int[] selectArr = selectSort(arr);
        System.out.println("选择："+Arrays.toString(selectArr));
        int[] insertArr = insertSort(arr);
        System.out.println("插入："+Arrays.toString(insertArr));
        int[] shellArr = shellSort(arr);
        System.out.println("希尔："+Arrays.toString(shellArr));
        int[] arr2 = {99,35,93,21,8,58,80,68};
        quicklySort(arr2, 0, arr2.length-1);
        System.out.println("快速："+Arrays.toString(arr2));
        int[] mergeArr = {99,35,93,21,8,58,80,68};
        int[] temp = new int[mergeArr.length];
        mergeSort(mergeArr, 0, mergeArr.length-1, temp);
        System.out.println("归并："+Arrays.toString(mergeArr));
        int[] radixArr = {99,35,93,21,8,58,80,68};
        radixSort(radixArr);
        System.out.println("基数："+Arrays.toString(radixArr));
    }

    /**
     * 冒泡 avg-o(n^2) baddest-o(n^2)
     * @param arr
     * @return
     */
    public static int[] bubbleSort(int[] arr){
        int[] returnArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            returnArr[i] = arr[i];
        }
        int temp;
        boolean flag = false;
        for (int i = 0; i < returnArr.length-1; i++) {
            flag = false;
            for (int j = i+1; j < returnArr.length; j++) {
                //如果发生交换，flag为true
                if(returnArr[i]>returnArr[j]){
                    flag = true;
                    temp = returnArr[i];
                    returnArr[i] = returnArr[j];
                    returnArr[j] = temp;
                }
            }
            //没有发生交换，已经有序，无需继续
            if(!flag)break;
        }
        return returnArr;
    }

    /**
     * 选择 avg-o(n^2) baddest-o(n^2)
     * @param arr
     * @return
     */
    public static int[] selectSort(int[] arr){
        int[] returnArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            returnArr[i] = arr[i];
        }
        int min;
        int minIndex;
        for (int i = 0; i < returnArr.length-1; i++) {
            min = returnArr[i];
            minIndex = i;
            for (int j = i+1; j < returnArr.length; j++) {
                if(returnArr[j]<min){
                    min = returnArr[j];
                    minIndex = j;
                }
            }
            returnArr[minIndex] = returnArr[i];
            returnArr[i] = min;
        }
        return returnArr;
    }

    /**
     * 插入 avg-o(n^2) baddest-o(n^2)
     * @param arr
     * @return
     */
    public static int[] insertSort(int[] arr){
        int[] returnArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            returnArr[i] = arr[i];
        }
        int insertVal;
        int insertIndex;
        for (int i = 0; i < returnArr.length-1; i++) {
            insertVal = returnArr[i+1];
            insertIndex = i;
            while (insertIndex>=0 && insertVal < returnArr[insertIndex]){
                returnArr[insertIndex+1] = returnArr[insertIndex];
                insertIndex--;
            }
            returnArr[insertIndex+1] = insertVal;
        }
        return returnArr;
    }

    /**
     * 希尔 avg-o(nlogn) baddest-o(n^s) 1<s<2
     * @param arr
     * @return
     */
    public static int[] shellSort(int[] arr){
        int[] returnArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            returnArr[i] = arr[i];
        }

        int insertIndex;
        int insertVal;
        for (int gap = arr.length/2; gap > 0; gap/=2) {
            //对于每一组进行插入排序
            for (int i = gap; i < arr.length; i++) {
               insertIndex = i;
               insertVal = returnArr[i];
               while (insertIndex-gap >= 0 && insertVal < returnArr[insertIndex-gap]){
                   returnArr[insertIndex] = returnArr[insertIndex-gap];
                   insertIndex-=gap;
               }
               returnArr[insertIndex] = insertVal;
            }
        }
        return returnArr;
    }

    /**
     * 快速 avg-o(nlogn) baddest-o(n^2)
     * @param arr
     * @return
     */
    public static void quicklySort(int[] arr,int left,int right){
        int l = left;
        int r = right;
        int pivot = arr[(left+right)/2];
        int temp;
        while (l<r){
            while (arr[l]<pivot){
                l++;
            }
            while (arr[r]>pivot){
                r--;
            }
            if(l>=r)break;
            temp = arr[r];
            arr[r] = arr[l];
            arr[l] = temp;

            if(arr[l] == pivot){
                r--;
            }
            if(arr[r] == pivot){
                l++;
            }
        }
        if(l==r){
            l++;
            r--;
        }
        if(left<r){
            quicklySort(arr,left, r);
        }
        if(right>l){
            quicklySort(arr,l,right);
        }
    }

    /*------------------------------归并排序开始--------------------------------------*/

    /**
     * 拆分-合并过程 avg-o(nlogn) baddest-o(nlogn)
     * @param arr
     * @param left
     * @param right
     * @param temp
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp){
        //只为求出mid,所以使用if
        if(left<right){
            int mid = (left+right)/2;
            mergeSort(arr,left,mid,temp);
            mergeSort(arr,mid+1,right, temp);
            merge(arr,left,right,mid,temp);
        }
    }

    /**
     * 归并-合并过程
     * @param arr
     * @param left
     * @param right
     * @param mid
     */
    public static void merge(int[] arr, int left, int right, int mid, int[] temp){
        int l = left;
        int r = mid+1;
        int index = 0;
        //1、把左右两组中较小的放入temp，直至其中一组结束
        while (l<=mid && r<=right){
            if(arr[l]<arr[r]){
                temp[index++]=arr[l++];
            }
            else {
                temp[index++]=arr[r++];
            }
        }
        //2、把有剩余的数组放入temp
            while (l<=mid){
                temp[index++] = arr[l++];
            }
            while (r<=right){
                temp[index++] = arr[r++];
            }
        //3、把temp放入arr
        int tempLeft = left;
            index = 0;
        while (tempLeft<=right){
            arr[tempLeft++] = temp[index++];
        }
    }

    /*------------------------------归并排序结束--------------------------------------*/

    /**
     * 基数 avg-o(logrB) baddest-o(logrB)
     * 最好不要出现负数，不适用于海量数据
     */
    public static void  radixSort(int[] arr){
        int[][] bucket = new int[10][arr.length];
        int[] bucketElementCount = new int[10];
        int max = arr[0];
        int maxLength;

        for (int i = 0; i < arr.length; i++) {
            if(arr[i] > max){
                max = arr[i];
            }
        }
        maxLength = (max+"").length();
        int index;
        for (int i = 0,n=1; i < maxLength; i++,n*=10) {
            index = 0;
            for (int l = 0; l < bucketElementCount.length; l++) {
                bucketElementCount[l] = 0;
            }
            for (int j = 0; j < arr.length; j++) {
                int digit = arr[j]/n%10;
                bucket[digit][bucketElementCount[digit]++] = arr[j];
            }
            for (int j = 0; j < bucket.length; j++) {
                if(bucketElementCount[j]!=0){
                    for (int k = 0; k < bucketElementCount[j]; k++) {
                        arr[index++] = bucket[j][k];
                    }
                }
            }
        }
    }
}
