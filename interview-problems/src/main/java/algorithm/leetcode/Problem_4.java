package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2021-03-02 16:49:01
 * 双数组的中位数。
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/xun-zhao-liang-ge-you-xu-shu-zu-de-zhong-wei-s-114/
 * <p>
 * 思路：设定上数组为短。如果不是则交换，保证上短下长。然后有分割线，分割线即为中位线。
 */
public class Problem_4 {
    public static void main(String[] args) {
        int[] nums1 = {2};
        int[] nums2 = {1};
        Problem_4 p = new Problem_4();
//        double medianSortedArrays = p.findMedianSortedArrays(nums1, nums2);
//        System.out.println(medianSortedArrays);

        System.out.println(p.findMedianSortedArrays2(nums1, nums2));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //思路：假设奇数时，分割线的左边为中位数，例如4、5的中位数都是2.那么左边的数量就为(n+m+1)/2。即把上下两
        //数组的数量放在一起来统计。
        //保证上短下长
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        int m = nums1.length;
        int n = nums2.length;

        //左边的总数
        int leftTotal = (m + n + 1) / 2;

        //在nums1的区间[0,m]里查找到恰当分割线
        //需要满足条件nums1[i-1] <= nums2[j] && nums2[j-1] <= nums1[i] 即分割线左边小于右边
        int left = 0;
        int right = m;
        //二分来查找正确的分割线位置，只需要在上数组使用二分查找即可
        while (left < right) {
            //nums1下标
            int i = left + (right - left + 1) / 2;
            //左边的总数确定的，那么同理也可以确定j，即nums2下标
            int j = leftTotal - i;
            //如果上左大于下右，那么说明应该往左找
            if (nums1[i - 1] > nums2[j]) {
                //从left到i-1搜索
                right = i - 1;
            } else {
                //从i-m搜索
                //假设left为2 right为3，那么i=left+(right-left)/2永远是i=left，无法跳出循环
                left = i;
            }
        }

        //找到了分割线，还需要处理特殊情况。
        int i = left;
        int j = leftTotal - i;
        //如果分割线左边没有元素，nums1全在分割线右边
        int nums1LeftMax = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
        int nums1RightMin = i == m ? Integer.MAX_VALUE : nums1[i];
        int nums2LeftMax = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
        int nums2RightMin = j == n ? Integer.MAX_VALUE : nums2[j];

        //奇数选取分割线左边最大
        if ((n + m) % 2 == 1) {
            return Math.max(nums1LeftMax, nums2LeftMax);
        } else {
            return (Math.max(nums1LeftMax, nums2LeftMax) + Math.min(nums1RightMin, nums2RightMin)) / 2.0;
        }
    }

    //时间复杂度也是o(log(m+n))，但是这个写法和思路更简单.参考解法3.把问题转化为在有序数组中找第k小的数，比如1，2，3中找第2小
    //的数就是他的中位数。由于需要找的是第k小的数，因此k/2的数是不关心的，可以通过k/2来去除一部分元素，然后再对剩余数组寻找。为
    //什么是k/2?因为分成上下两个数组，用k/2可以拆分到两个数组里单独去判断。两个数组中各自在k/2的位置上比较，小的数由于正序的原
    //因，一定是比第k个数小的，因此可以直接舍弃[0(当前剩余数组的最左边),k/2]部分元素，然后找第k-(k/2)个最小元素(因为正序中
    // 已经去掉了k/2).特殊情况1、是k=1时，直接找两个数组中第1小的元素即可.2、两个数组位置上元素相等的情况，移除哪一个都可以，
    //可以假设为移除下面的。3、当其中某一个数组长度小于k/2时，可以直接指向末尾，舍弃掉该数组，然后在另一数组找第k小的
    //https://leetcode.cn/problems/median-of-two-sorted-arrays/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-2/
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        //合并奇数情况和偶数情况.奇数情况left和right一样的
        int left = (n + m + 1) / 2;
        int right = (n + m + 2) / 2;

        //(左+右) /2 。奇数也通用处理了
        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;
    }

    /**
     * 两正序数组中找第k小元素
     *
     * @param nums1
     * @param start1
     * @param end1
     * @param nums2
     * @param start2
     * @param end2
     * @param k
     * @return
     */
    public int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;

        //保证nums1长度小于2.这里必须在递归中来处理，事先排序无法解决两数组长度相等的情况
        if(len1 > len2) return getKth(nums2,start2,end2,nums1,start1,end1,k);

        if (len1 == 0) return nums2[start2 + k - 1];

        if (k == 1) return Math.min(nums1[start1], nums2[start2]);

        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        if (nums1[i] <= nums2[j]) {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        } else {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        }
    }
}
