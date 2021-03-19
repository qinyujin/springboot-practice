package algorithm.offer;

/**
 * @author :覃玉锦
 * @create :2021-01-28 14:23:00
 * 数组中的逆序对
 * https://www.nowcoder.com/practice/96bd6684e04a44eb80e6a68efc0ec6c5?tpId=13&tqId=11188&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007
 */
public class Problem_51 {
    public static void main(String[] args) {
        Problem_51 problem51 = new Problem_51();
        int[] arr = {1,2,3,4,5,6,7,0};
        System.out.println(problem51.InversePairs(arr));
    }
    private int cnt = 0;

    public int InversePairs(int [] array) {
        int[] temp = new int[array.length];
        mergeSort(array,0,array.length-1,temp);
        return this.cnt;
    }

    public void mergeSort(int[] arr,int left,int right,int[] temp){
        if(left<right){
            int mid = (left+right)/2;
            mergeSort(arr,left,mid,temp);
            mergeSort(arr,mid+1,right,temp);
            merge(arr,left,mid,right,temp);
        }
    }

    public void merge(int[] arr,int left,int mid,int right,int[] temp){
        //合并的三步：
        //1、把两个值中较小的放入temp
        //2、把右剩余的数组放入temp
        //3、把temp重新赋给arr
        int i = left;
        int j = mid+1;
        int t = 0;
        //1
        while (i <= mid && j <= right){
            if(arr[i] <= arr[j]){
                temp[t++] = arr[i++];
            }
            else {
                temp[t++] = arr[j++];
                //i>j 说明i-mid都是大于右边，都逆序，逆序数量为mid+1-i
                //mid+1：右边第一个数的下标 mid+1-i：由于左边是排序好的，因此从i到mid都是大于右边的，都需要逆序交换
                cnt = (cnt + (mid-i+1))%1000000007;
            }
        }
        //2
        while (i <= mid){
            temp[t++] = arr[i++];
        }
        while (j<= right){
            temp[t++] = arr[j++];
        }
        //3
        t = 0;
        int tempLeft = left;
        while (tempLeft<=right){
            arr[tempLeft++] = temp[t++];
        }
    }
}
