package algorithm.leetcode;

import java.util.PriorityQueue;

/**
 * @author :覃玉锦
 * @create :2021-03-13 14:49:00
 * 数组中的第k个最大元素
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 */
public class Problem_215 {
    public static void main(String[] args) {
        Problem_215 p = new Problem_215();
        int[] nums = {3,2,3,1,2,4,5,5,6};
        System.out.println(p.findKthLargest(nums, 4));
    }

    //可以使用大顶堆。弹出k-1个数，那么堆顶就是第k大的数
    public int findKthLargest(int[] nums, int k) {
        //大顶堆
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1,o2) -> o2-o1);
        for (int num : nums) {
            queue.offer(num);
        }
        int s = 0;
        while (!queue.isEmpty()){
            s++;
            if(s==k)return queue.peek();
            queue.poll();
        }
        return 0;
    }
}
