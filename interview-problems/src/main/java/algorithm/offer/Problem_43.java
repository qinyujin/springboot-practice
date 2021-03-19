package algorithm.offer;

/**
 * @author :覃玉锦
 * @create :2021-01-27 20:22:00
 * 从1到n中1出现的次数
 * https://www.nowcoder.com/practice/bd7f978302044eee894445e244c7eee6?tpId=13&tqId=11184&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 */
public class Problem_43 {
    public static void main(String[] args) {
        System.out.println(new Problem_43().NumberOf1Between1AndN_Solution(13));
    }

    //以13举例。个位数是1的有1、11.十位数是1的有10、11、12、13.因此答案是6.
    //思路是按位数来计算1的个数。比如计算个位数的1：(13+8)/10 = 2 十位数：(1+8)/10*10 + (3+1)
    //上述公式解释：个位数计算为什么要+8？因为要进位计算，像这里13/10=1，那么+8进位为21/10=2.正好是个位的数量。
    //但是像10、11这两个数就不会进位，因此特殊计算判断上一位是1还是0(a%10==1?b+1:0)。十位数的计算就是把n取到十位数，
    //如13->1 123->12。
    public int NumberOf1Between1AndN_Solution(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i*=10) {
            //例如21345.那么a是21345->2134->213...    b是0->5->45...
            int a = n/i,b = n%i;
            sum +=(a+8)/10*i + (a%10==1?b+1:0);
        }
        return sum;
    }
}
