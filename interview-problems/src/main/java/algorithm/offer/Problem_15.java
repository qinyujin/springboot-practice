package algorithm.offer;

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

    /**
     * 举例 10=1010
     * 如果需要求1的个数，1010&(1010-1=1001) -> 1010&1001=1000
     * 1000&(1000-1=0100) -> 1000&0100=0000
     * 操作次数总共为2，每次减少一个1
     * @param n
     * @return
     */
    public int NumberOf1(int n) {
        //利用位与操作，每次能减少一个1,因为n-1比n少一位，所以只会有一位变化
        int count = 0;
        while (n != 0) {
            count++;
            //由于是二进制，减一会变动两位，因此与操作会相当于减少原来的一位1.例如 1010 减一获得 1001 与操作减少一位1得到 1000
            n = n & (n - 1);
        }
        return count;
//        return Integer.bitCount(n);
    }
}
