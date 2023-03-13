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
        int[] nums = {1, 1, 1, 2, 2, 3};
        int[] ints = p.topKFrequent(nums, 2);
        System.out.println(Arrays.toString(ints));
    }

    public int[] topKFrequent(int[] nums, int k) {
        //nums[i] => count(nums[i])
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        //最好画图来看，维护count的小顶堆，元素超过k时移除堆顶，那么剩余的则是大的数
        PriorityQueue<Integer> minTopQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return map.get(a) - map.get(b);
            }
        });

        for (Integer key : map.keySet()) {
            if (minTopQueue.size() < k) {
                minTopQueue.add(key);
            } else if (map.get(key) < map.get(minTopQueue.peek())) {
                minTopQueue.remove();
                minTopQueue.add(key);
            }
        }

        List<Integer> res = new ArrayList<>();
        while (!minTopQueue.isEmpty()) {
            res.add(minTopQueue.remove());
        }

        int[] arr = new int[res.size()];
        int i = 0;
        for (Integer re : res) {
            arr[i++] = re;
        }
        return arr;
    }
}
