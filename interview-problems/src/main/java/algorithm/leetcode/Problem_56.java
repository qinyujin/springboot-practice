package algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-03-11 11:17:00
 * 合并区间
 * https://leetcode-cn.com/problems/merge-intervals/
 */
public class Problem_56 {
    public static void main(String[] args) {
        Problem_56 p =new Problem_56();
        int[][] intervals = {
                {1,3},
                {2,6},
                {8,10},
                {15,18}
        };
        int[][] merge = p.merge(intervals);
        for (int i = 0; i < merge.length; i++) {
            System.out.println(Arrays.toString(merge[i]));
        }
    }

    //思路：按照左端点先排序，然后通过按顺序遍历，如果下一个区间的左小于当前右，把当前右更新
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });
        List<int[]> merged = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int L = intervals[i][0],R = intervals[i][1];
            //如果当前区间L 大于 merge末尾R说明断开了
            if(merged.size()==0 || merged.get(merged.size()-1)[1] < L){
                merged.add(new int[]{L,R});
            }
            else {
                //更新R为最大值
                merged.get(merged.size()-1)[1] = Math.max(merged.get(merged.size()-1)[1],R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
}
