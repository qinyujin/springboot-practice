package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2021-03-15 17:12:00
 * 寻找重复数
 * https://leetcode-cn.com/problems/find-the-duplicate-number/
 */
public class Problem_287 {
    public static void main(String[] args) {
        Problem_287 p = new Problem_287();
//        int[] nums = {1,3,4,2,2};
//        int[] nums = {3,1,3,4,2};
//        int[] nums = {1,1};
        int[] nums = {1, 1, 2};
        System.out.println(p.findDuplicate(nums));
    }

    //使用o(1) 空间来解决。如果不要求o(1) 可以使用哈希来解决。要求o(1) 可以通过交换法，把对应数字交换到对应位置。题目要求不能
    //修改原数组，这种方式是修改了数组的
    //1,3,4,2,2
    public int findDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int curValue = nums[i];
            if (curValue != i) {
                //放到对应下标
                if (curValue == nums[curValue]) {
                    return curValue;
                }
                int temp = nums[curValue];
                nums[curValue] = curValue;
                nums[i] = temp;
                i--;
            } else {
                continue;
            }
        }
        return 0;
    }

    //不修改原数组，空间o1的解法.https://leetcode.cn/problems/find-the-duplicate-number/solution/287xun-zhao-zhong-fu-shu-by-kirsche/
    //构建一条链表，指向为 i -> nums[i].重复的节点在此题的条件下(n+1个数、1个重复值，范围[1,n])会构成循环链表，找到循环节点即可。为什么
    //一定会构成环?因为出现了重复的映射，理论上会映射到同一节点
    //链表和数组的对应关系：x.next = y  <==> nums[x] = y
    public int findDuplicate2(int[] nums) {
        int fast = nums[nums[0]], slow = nums[0];
        while (fast != slow) {
            //fast.next.next
            fast = nums[nums[fast]];
            //slow.next
            slow = nums[slow];
        }

        int pre1 = 0, pre2 = slow;
        while (pre1 != pre2) {
            pre1 = nums[pre1];
            pre2 = nums[pre2];
        }

        return pre1;
    }
}
