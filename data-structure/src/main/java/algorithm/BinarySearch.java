package algorithm;

/**
 * @author :覃玉锦
 * @create :2020-12-16 15:19:00
 * 二分查找非递归形式
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1,3,8,10,11,67,100};
        int i = binarySearch(arr, -8);
        System.out.println("index="+i);
    }

    /**
     * 二分查找的非递归形式
     *
     * @param arr    检索的数组集
     * @param target 查找的值
     * @return 值对应的下标，找不到返回-1
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else if(arr[mid] < target){
                left = mid + 1;
            }
        }
        return -1;
    }
}
