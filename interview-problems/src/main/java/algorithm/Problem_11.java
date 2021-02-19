package algorithm;

/**
 * @author :覃玉锦
 * @create :2021-01-21 20:35:00
 * 旋转数组的最小数字
 * https://www.nowcoder.com/practice/9f3231a991af4f55b95579b44b7a01ba?tpId=13&tqId=11159&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 *
 * input：
 * [3,4,5,1,2]
 * output：
 * 1
 *
 * 本题就是一个查找的想法，但是不是顺序的数组，因此不能直接使用二分法，那么可以改造一下条件来使用二分。
 */
public class Problem_11 {
    public static void main(String[] args) {
        int[] arr = {3, 4, 5, 1, 2};
        Problem_11 rm = new Problem_11();
        System.out.println(rm.minNumberInRotateArray(arr));
    }

    public int minNumberInRotateArray(int [] array) {
        if (array.length == 0)
            return 0;
        int l = 0, h = array.length - 1;
        while (l < h) {
            //m为一段区间的中点值。
            int m = l + (h - l) / 2;
            //target 为末尾元素，如果mid的值<末尾值说明是递增，也就是说旋转数组在另一段
            if (array[m] <= array[h])
                h = m;
            else
                l = m + 1;
        }
        return array[l];
    }

}
