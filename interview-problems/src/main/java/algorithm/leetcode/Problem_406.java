package algorithm.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author :覃玉锦
 * @create :2021-03-18 17:24:00
 * 根据身高重建队列
 * https://leetcode-cn.com/problems/queue-reconstruction-by-height/
 */
public class Problem_406 {
    public static void main(String[] args) {
        Problem_406 p = new Problem_406();
        int[][] people = {
                {7,0},
                {4,4},
                {7,1},
                {5,0},
                {6,1},
                {5,2}
        };
        int[][] ints = p.reconstructQueue(people);
        for (int[] anInt : ints) {
            System.out.println(Arrays.toString(anInt));
        }
    }

    //[[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]] 身高，前面大于等于该身高的数量
    //思路是可以先按照身高来入队列，如果遇到数量小当前res长度，那么就选择插入，到index位置，保证前面的大于它的数量小于
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people,new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] != o2[0] ? o2[0] - o1[0] : o1[1] - o2[1];
            }
        });
        int[][] res = new int[people.length][2];
        for (int i = 0; i < people.length; i++) {
            //如果数量大于当前下标
            int target = people[i][1];
            if(target >= i){
                res[i] = people[i];
            }
            else {
                //后移
                for (int j = i; j > target; j--) {
                    res[j] = res[j-1];
                }
                //插入
                res[target] = people[i];
            }
        }
        return res;
    }
}
