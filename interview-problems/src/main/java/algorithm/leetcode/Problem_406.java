package algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-03-18 17:24:01
 * 根据身高重建队列
 * https://leetcode-cn.com/problems/queue-reconstruction-by-height/
 */
public class Problem_406 {
    public static void main(String[] args) {
        Problem_406 p = new Problem_406();
        int[][] people = {
                {7, 0},
                {4, 4},
                {7, 1},
                {5, 0},
                {6, 1},
                {5, 2}
        };
        int[][] ints = p.reconstructQueue(people);
        for (int[] anInt : ints) {
            System.out.println(Arrays.toString(anInt));
        }
    }

    //[[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]] 身高，前面大于等于该身高的数量
    //通过排序后插入的方式。排序可以保证大致的顺序，比如按照身高降序、k升序，然后通过插入到数组k位置保证相对顺序。排序保证大致的属性可以满足
    //比如k前面是大于等于
    public int[][] reconstructQueue(int[][] people) {
        //按照身高降序、二级按照k升序
        Arrays.sort(people, (o1, o2) -> o1[0] != o2[0] ? o2[0] - o1[0] : o1[1] - o2[1]);
        List<int[]> list = new ArrayList<>();
        for (int[] person : people) {
            //插入到k位置
            list.add(person[1],person);
        }
        return list.toArray(new int[0][0]);
    }
}
