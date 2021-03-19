package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2021-03-09 12:52:00
 * 搜索旋转排序数组
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
 */
public class Problem_33 {
    public static void main(String[] args) {
        Problem_33 p = new Problem_33();
        int[] nums = {4,5,6,7,0,1,2};
        System.out.println(p.search(nums, 3));
    }

    public int search(int[] nums, int target) {
        //二分查找。当arr[mid] < arr[high] 就说明区间[mid,high]是递增的，说明旋转数组在另外一个区间
        int l = 0,r = nums.length-1;
        while (l<=r){
            int mid = (l+r)/2;
            if(nums[mid] == target){
                return mid;
            }
            //通过mid区分出两个区间：[0,mid] [mid+1,n-1]
            //由于旋转，必定有一个是递增区间。就通过递增区间来判断
            //左边是递增区间
            else if(nums[0] <= nums[mid]){
                if(nums[0] <= target && target < nums[mid]){
                    r = mid-1;
                }
                else {
                    l = mid+1;
                }
            }
            //右边是递增区间，可以结合图示看一下，会发现右边的旋转部分全部比左边有序小。那么如果是右边递增，如果查找不再这个
            //范围之内，就需要往左边区间去找。
            else {
                if(nums[mid] < target && target <= nums[nums.length-1]){
                    l = mid+1;
                }
                else {
                    r = mid-1;
                }
            }
        }
        return -1;
    }
}
