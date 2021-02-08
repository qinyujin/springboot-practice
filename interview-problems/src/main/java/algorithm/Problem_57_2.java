package algorithm;

import java.util.ArrayList;

/**
 * @author :覃玉锦
 * @create :2021-01-28 19:43:00
 * 和为S的连续正数序列
 * https://www.nowcoder.com/practice/c451a3fd84b64cb19485dad758a55ebe?tpId=13&tqId=11194&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!
 */
public class Problem_57_2 {
    public static void main(String[] args) {
        int sum = 9;
        System.out.println(new Problem_57_2().FindContinuousSequence(sum));
    }

    /**
     * 由于是连续的数列，那么设置一个start和end来连续的找
     * @param sum
     * @return
     */
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        int start = 1,end = 2;
        int curSum = 3;
        ArrayList<ArrayList<Integer>> retList = new ArrayList<>();
        while (end < sum){
            if(curSum < sum){
                end++;
                curSum+=end;
            }
            else if(curSum > sum){
                curSum-=start;
                start++;
            }
            else {
                ArrayList<Integer> list = new ArrayList();
                for (int i = start; i <= end; i++) {
                    list.add(i);
                }
                retList.add(list);
                curSum-=start;
                start++;
                end++;
                curSum+=end;
            }
        }
        return retList;
    }
}
