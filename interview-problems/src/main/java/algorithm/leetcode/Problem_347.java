package algorithm.leetcode;

import java.util.*;

/**
 * @author :覃玉锦
 * @create :2021-03-17 08:57:00
 * 前k个高频元素
 * https://leetcode-cn.com/problems/top-k-frequent-elements/
 */
public class Problem_347 {
    public static void main(String[] args) {
        Problem_347 p = new Problem_347();
        int[] nums = {1,1,1,2,2,3};
        int[] ints = p.topKFrequent(nums, 2);
        System.out.println(Arrays.toString(ints));
    }

    public int[] topKFrequent(int[] nums, int k) {
        //先统计nums中的元素数量
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        //通过小顶堆来把数量最小的给推出去，最后只保留最大数量，数组[0]为key即数字,数组[1]为value即数量
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (queue.size() == k) {
                //如果堆顶比现在的还小，那么把当前的元素加入到堆中
                if(queue.peek()[1] < entry.getValue()){
                    queue.poll();
                    queue.offer(new int[]{entry.getKey(),entry.getValue()});
                }
            } else {
                //0为key，1为value，堆会根据数量构建小顶
                queue.offer(new int[]{entry.getKey(), entry.getValue()});
            }
        }
        System.out.println(queue.peek()[0]);
        //最后的堆中只剩两个最大数量的值
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = queue.poll()[0];
        }
        return result;
    }
}
