package algorithm.offer;

/**
 * @author :覃玉锦
 * @create :2021-01-27 16:37:00
 * 数组中出现次数超过一半的数字
 * https://www.nowcoder.com/practice/e8a1b01a2df14cb2b228b30ee6a92163?tpId=13&tqId=11181&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 */
public class Problem_39 {
    public static void main(String[] args) {
        int[] arr = {1,2,3,2,2,2,5,4,2};
        System.out.println(new Problem_39().MoreThanHalfNum_Solution(arr));
    }

    // 1,2,3,2,2,2,5,4,2
    public int MoreThanHalfNum_Solution(int [] array) {
        //从第一个数开始统计，需要找出出现次数最多的数字
        int majority = array[0];
        for (int i = 1, cnt = 1; i < array.length; i++) {
            //相同则数量+1.
            cnt = array[i] == majority ? cnt + 1 : cnt - 1;
            //如果数量为0，那么就需要换一个值来统计
            if (cnt == 0) {
                majority = array[i];
                cnt = 1;
            }
        }
        int cnt = 0;
        for (int val : array)
            if (val == majority)
                cnt++;
        return cnt > array.length / 2 ? majority : 0;
    }
}
