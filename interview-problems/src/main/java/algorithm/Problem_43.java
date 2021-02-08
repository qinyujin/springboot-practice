package algorithm;

/**
 * @author :覃玉锦
 * @create :2021-01-27 20:22:00
 * 从1到n中1出现的次数
 * https://www.nowcoder.com/practice/bd7f978302044eee894445e244c7eee6?tpId=13&tqId=11184&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 */
public class Problem_43 {
    public static void main(String[] args) {
        System.out.println(new Problem_43().NumberOf1Between1AndN_Solution(100));
    }

    //1
    //10 11 12 13 14
    public int NumberOf1Between1AndN_Solution(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i*=10) {
            int a = n/i,b = n%i;
            sum +=(a+8)/10*i + (a%10==1?b+1:0);
        }
        return sum;
    }
}
