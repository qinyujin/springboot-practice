package algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * @author :覃玉锦
 * @create :2023-03-17 13:26:00
 *
 * https://leetcode.cn/problems/insert-delete-getrandom-o1/
 */
public class Problem_380 {
    public static void main(String[] args) {
        Problem_380 p = new Problem_380();
        p.insert(1);
        p.remove(2);
        p.insert(2);
        p.getRandom();
        p.remove(1);
        p.insert(2);
        p.getRandom();
    }

    private List<Integer> nums = new ArrayList<>();
    //存储下标
    private HashMap<Integer,Integer> indexes = new HashMap<>();
    public boolean insert(int val) {
        if(indexes.containsKey(val)) return false;
        int size = nums.size();
        indexes.put(val,size);
        nums.add(val);
        return true;
    }

    public boolean remove(int val) {
        if(!indexes.containsKey(val))return false;
        Integer index = indexes.get(val);
        Integer last = nums.get(nums.size() - 1);
        nums.set(index,last);
        indexes.put(last, index);
        nums.remove(nums.size()-1);
        indexes.remove(val);
        return true;
    }

    public int getRandom() {
        int i = new Random().nextInt(nums.size());
        return nums.get(i);
    }
}
