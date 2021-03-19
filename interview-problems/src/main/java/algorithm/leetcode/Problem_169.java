package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2021-03-12 16:37:00
 * 多数元素
 * https://leetcode-cn.com/problems/majority-element/
 */
public class Problem_169 {
    public static void main(String[] args) {
        Problem_169 p =new Problem_169();
        int[] nums = {2,2,1,1,1,2,2};
        System.out.println(p.majorityElement(nums));
    }

    //使用major代表其中的数字，从第一个开始，当统计到相同的元素sum++，不同--。如果sum=0了，就更换major。
    //由于超过半数，所以最后保存的肯定是sum>0
    public int majorityElement(int[] nums) {
        int majority = nums[0];
        for (int i = 1,cnt = 1; i < nums.length; i++) {
            cnt = nums[i] == majority ? cnt+1 : cnt-1;
            if(cnt == 0){
                majority = nums[i];
                cnt = 1;
            }
        }
        return majority;
    }
}
