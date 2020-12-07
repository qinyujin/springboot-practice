package dataStructure.sort;

import java.util.Arrays;

/**
 * @author :覃玉锦
 * @create :2020-11-28 14:05:00
 * 归并排序，拆分、分治
 * avg-o(nlogn) baddest-o(nlogn)
 */
public class GuiBing {
    public static void main(String[] args) {
        /*int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i]=(int)(Math.random()*800000);
        }*/
        //8个数合并了7次，n个数合并n-1次，合并效率为O(n)
//        int[] arr = {8,4,5,7,1,3,6,2};
        int[] arr = {99,35,93,21,8,58,80,68};

        int[] temp =new int[arr.length];
        long begin = System.currentTimeMillis();
        mergeSort(arr,0,arr.length-1,temp);
        long end = System.currentTimeMillis();
        System.out.println(arr.length+"条数据归并排序耗费时间："+(end-begin));

        System.out.println(Arrays.toString(arr));
    }

    /**
     *   把数组分、合并
     * @param arr
     * @param left
     * @param right
     * @param temp
     */
    public static void mergeSort(int[] arr,int left,int right,int[] temp){
        //对左右进行拆分
        if(left<right){
            int mid = (left+right)/2;
            //左边拆分
            mergeSort(arr, left, mid, temp);
            //右边拆分
            mergeSort(arr,mid+1, right,temp);
            //拆完之后拿到mid，进行合并
            merge(arr,left,mid,right,temp);
        }
    }

    /**
     *  合并的思路：1、把左右两个数组根据索引位置的值较小的数放入temp，直至任一数组结束
     *  2、把有数剩余的数组接着放入temp
     *  3、把temp赋值给arr
     *
     * @param arr  原始数组
     * @param left  左边有序序列的初始索引
     * @param mid  中间索引
     * @param right  右边索引
     * @param temp  做中转的数组
     */
    public static void merge(int[] arr,int left,int mid,int right,int[] temp){
        int i = left;//左边数组初始索引
        int j = mid+1;//右边数组初始索引
        int t = 0;//temp数组初始索引

        //1、把左右数组较小值放入temp
        //当左边|右边数组没有结束
        while (i <= mid && j <= right){
            //把较小值放入tmep
            if(arr[i]<arr[j]){
                temp[t] = arr[i];
                t+=1;
                i+=1;
            }
            else {
                temp[t] = arr[j];
                t+=1;
                j+=1;
            }
        }

        //2、把剩余数组放入temp
        //把剩余数组放入tmep
        while (i <= mid){
            temp[t] = arr[i];
            i+=1;
            t+=1;
        }

        while (j <= right) {
            temp[t] = arr[j];
            j += 1;
            t += 1;
        }

        //3、把temp数组赋值给arr
        t = 0;
        int tempLeft = left;
        //并不是每次都是0-7.根据图片来看可能还会有0-1,2-3等情况
        while (tempLeft <= right){
            arr[tempLeft] = temp[t];
            t+=1;
            tempLeft+=1;
        }
    }
}
