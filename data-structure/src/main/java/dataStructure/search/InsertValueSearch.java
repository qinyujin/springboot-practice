package dataStructure.search;

/**
 * @author :覃玉锦
 * @create :2020-12-07 21:26:00
 * 插值，基于二分，区别是mid不是每次都是中间值，而是一个自适应的值。对于数据量较大，关键字分布均匀的数据来说插值比较好，不均匀的话
 * 不一定比二分好
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        /*int[] arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = i+1;
        }*/
//        int[] arr = {1,8,10,89,1000,1234};
        int[] arr = {1,2,3,4,5,6,7,8};
        int index = insertValueSearch(arr, 0, arr.length - 1, 6);
        int index1 = binarySearch(arr, 0, arr.length - 1, 6);

    }

    /**
     * 插值查找,mid公式：
     * int mid = left + (right – left) * (findVal – arr[left]) / (arr[right] – arr[left])
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return
     */
    public static int insertValueSearch(int[] arr,int left,int right,int findVal){
        System.out.println("插值调用");
        int mid = left + (right-left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if(left>right || findVal<arr[0] || findVal>arr[arr.length-1]){
            return -1;
        }
        if(findVal > midVal){
            return insertValueSearch(arr,mid+1,right, findVal);
        }
        else if(findVal<midVal){
            return insertValueSearch(arr,mid-1,left,findVal);
        }
        else return mid;
    }

    public static int binarySearch(int[] arr,int left,int right,int findVal){
        System.out.println("二分调用");
        int mid = (left+right)/2;
        int midVal = arr[mid];
        if(left>right || findVal<arr[0] || findVal>arr[arr.length-1]){
            return -1;
        }
        if(findVal > midVal){
            return binarySearch(arr,mid+1,right, findVal);
        }
        else if(findVal<midVal){
            return binarySearch(arr,mid-1,left,findVal);
        }
        else return mid;
    }
}
