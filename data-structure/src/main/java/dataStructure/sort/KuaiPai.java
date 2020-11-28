package dataStructure.sort;

/**
 * @author :覃玉锦
 * @create :2020-11-28 09:48:00
 * 快速排序法
 */
public class KuaiPai {
    public static void main(String[] args) {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i]=(int)(Math.random()*800000);
        }
//        int[] arr = {-9,78,0,23,-567,70};
        long begin = System.currentTimeMillis();
        quickSort(arr,0 , arr.length-1);
        long end = System.currentTimeMillis();
        System.out.println("快排耗费时间："+(end-begin));
    }

    /**
     * 快速排序法，简称快排
     * 核心思想就是以中轴线的数为基准，左边的全部比它小，右边的全部比它大进行排序
     */
    public static void quickSort(int[] arr,int left,int right){
        int l = left;
        int r = right;
        //中心
        int pivot = arr[(left+right)/2];
        int temp = 0;
        //把中心数进行分左右的排序
        while (l<r){
            //找到左边大于等于pivot的数，这个数不会超过pivot，因为arr[l]最右等于pivot
            while (arr[l] < pivot){
                l+=1;
            }
            //右边同理
            while (arr[r] > pivot){
                r-=1;
            }

            //不能让l和r相等
            if(l>=r){
                break;
            }
            //交换两个值
            temp = arr[l];
            arr[l]=arr[r];
            arr[r]=temp;

            //如果没有这一判断，遇到两个相同的值会死循环，因为左右停留在同一位置，比如-9,78,0,23,23,-567,70，则下标会停留在3,4
            //交换完之后，如果arr[l] = pivot ，右坐标左移
            if(arr[l] == pivot){
                r-=1;
            }
            //左边同理
            if(arr[r] == pivot){
                l+=1;
            }
        }
        //用于处理只剩两个数的情况,如果不处理，会一直递归，导致栈溢出
        if(l==r){
            l+=1;
            r-=1;
        }
        //把左边部分和右边部分分别递归进行调用即可
        //左递归
        if(left<r){
            quickSort(arr, left, r);
        }
        if(right>l){
            quickSort(arr, l, right);
        }
    }
}
