package algorithm.leetcode;

/**
 * @author :覃玉锦
 * @create :2021-03-13 10:36:00
 * 汉明距离
 * https://leetcode-cn.com/problems/hamming-distance/
 */
public class Problem_461 {
    public static void main(String[] args) {
        Problem_461 p = new Problem_461();
        System.out.println(p.hammingDistance(1, 4));
    }

    // x = 1, y = 4 -> 输出2  因为二进制有两个位置不同
    public int hammingDistance(int x, int y) {
        //异或可以把不同位置变为1，然后通过&(n-1)来统计1的个数即可
        int diff = x ^ y;
        int count = 0;
        while (diff != 0) {
            //每次与n-1操作可以减少一个1,原理是n-1一定是最后一位不同
            diff &= (diff - 1);
            count++;
        }
        return count;
    }
}
