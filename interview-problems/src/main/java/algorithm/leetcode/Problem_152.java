package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2021-03-12 16:09:00
 * 乘积最大子序列
 * https://leetcode-cn.com/problems/maximum-product-subarray/solution/hua-jie-suan-fa-152-cheng-ji-zui-da-zi-xu-lie-by-g/
 */
public class Problem_152 {
    public static void main(String[] args) {
        Problem_152 p = new Problem_152();
        int[] nums = {-2,0,-1};
        System.out.println(p.maxProduct(nums));
    }

    //思路:遍历整个数组来统计当前的最大值，max存储最大，imax存储当前位置最大， imin存最小，可以在遍历过程中统计imax*nums[i]
    //的值，然后保留最大的。由于有负数的存在，所以使用imin来作为交换。当nums[i]小于0，最大最小性质交换，所以交换imax和imin
    //以达到imax保证位置最大。
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE,imax = 1,imin = 1;
        for (int i = 0; i < nums.length; i++) {
            //由于负数会让最大的乘完变最小，所以这里需要交换。
            if(nums[i] < 0){
                int temp = imax;
                imax = imin;
                imin = temp;
            }
            imax = Math.max(imax*nums[i],nums[i]);
            imin = Math.min(imin*nums[i],nums[i]);
            max = Math.max(max,imax);
        }
        return max;
    }
}
