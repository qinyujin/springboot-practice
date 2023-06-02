package algorithm.leetcode;

import java.util.Arrays;

/**
 * @author :覃玉锦
 * @create :2021-03-17 08:51:00
 * 比特位计数
 * https://leetcode-cn.com/problems/counting-bits/
 */
public class Problem_338 {
    public static void main(String[] args) {
        Problem_338 p = new Problem_338();
        int[] ints = p.countBits(5);
        System.out.println(Arrays.toString(ints));
    }

    //奇数的1总比减一的偶数大1.因为增加的是最低位。如  0 1    10 11
    //偶数的1和它除2的1数量相等，因为偶数最低位是0，除2相当于右移一位，也就是移除掉0.如100 10
    public int[] countBits(int n) {
        //dp[i]: 数字i二进制包含1的数量
        int[] dp = new int[n + 1];
        //0的1数量为0
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            //奇数的话应该等于偶数+1
            if (i % 2 == 1) {
                dp[i] = dp[i - 1] + 1;
            }
            //i/2 == i>>1  >>:二进制右移一位
            else dp[i] = dp[i / 2];
        }
        return dp;
    }
}
