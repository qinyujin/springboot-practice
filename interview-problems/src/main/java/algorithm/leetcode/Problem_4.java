package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2021-03-02 16:49:00
 * 力扣热题第四题：
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/xun-zhao-liang-ge-you-xu-shu-zu-de-zhong-wei-s-114/
 * 双数组的中位数。
 *
 * 思路：设定上数组为短。如果不是则交换，保证上短下长。然后有分割线，分割线即为中位线。
 */
public class Problem_4 {
    public static void main(String[] args) {
        int[] nums1 = {0,0};
        int[] nums2 = {0,0};
        Problem_4 p = new Problem_4();
        double medianSortedArrays = p.findMedianSortedArrays(nums1, nums2);
        System.out.println(medianSortedArrays);
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //思路：假设奇数时，分割线的左边为中位数，例如4、5的中位数都是2.那么左边的数量就为(n+m+1)/2。即把上下两
        //数组的数量放在一起来统计。
        //保证上短下长
        if(nums1.length > nums2.length){
            int[] temp = nums1;
            nums1 =nums2;
            nums2 = temp;
        }

        int m = nums1.length;
        int n = nums2.length;

        //左边的总数
        int leftTotal = (m+n+1)/2;

        //在nums1的区间[0,m]里查找到恰当分割线
        //需要满足条件nums1[i-1] <= nums2[j] && nums2[j-1] <= nums1[i] 即分割线左边小于右边
        int left = 0;
        int right = m;
        //二分来查找正确的分割线位置，只需要在上数组使用二分查找即可
        while (left < right){
            //nums1下标
            int i = left+(right-left+1)/2;
            //左边的总数确定的，那么同理也可以确定j，即nums2下标
            int j = leftTotal-i;
            //如果上左大于下右，那么说明应该往左找
            if(nums1[i-1] > nums2[j]){
                //从left到i-1搜索
                right = i-1;
            }
            else {
                //从i-m搜索
                //如果这里是i，当区间在[left,right]时会死循环，所以i需要取值为i+1
                left = i;
            }
        }

        //找到了分割线，还需要处理特殊情况。
        int i =left;
        int j =leftTotal-i;
        //如果分割线左边没有元素，nums1全在分割线右边
        int nums1LeftMax = i==0 ? Integer.MIN_VALUE : nums1[i-1];
        int nums1RightMin = i==m ? Integer.MAX_VALUE : nums1[i];
        int nums2LeftMax = j==0 ? Integer.MIN_VALUE : nums2[j-1];
        int nums2RightMin = j==n ? Integer.MAX_VALUE : nums2[j];

        //奇数选取分割线左边最大
        if((n+m)%2==1){
            return Math.max(nums1LeftMax,nums2LeftMax );
        }
        else {
            return (Math.max(nums1LeftMax,nums2LeftMax) + Math.min(nums1RightMin,nums2RightMin))/2.0;
        }
    }
}
