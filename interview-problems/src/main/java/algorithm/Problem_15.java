package algorithm;

/**
 * @author :覃玉锦
 * @create :2021-01-22 14:02:00
 * 二进制中1的个数
 * https://www.nowcoder.com/practice/8ee967e43c2c4ec193b040ea7fbb10b8?tpId=13&tqId=11164&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 输入一个整数，输出该数32位二进制表示中1的个数。其中负数用补码表示。
 */
public class Problem_15 {
    public static void main(String[] args) {
        Problem_15 bon = new Problem_15();
        System.out.println(bon.NumberOf1(10));
    }

    public int NumberOf1(int n) {
        //利用位与操作，每次能减少一个1,因为n-1比n少一位，所以只会有一位变化
        int count = 0;
        while (n != 0) {
            count++;
            n = n & (n - 1);
        }
        return count;
//        return Integer.bitCount(n);
    }
}
